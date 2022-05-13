package domain;

import bootstrap.Driver;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DownloadContent {
    private String bucketName;
    private String keyName;
    private String contentPath;
    private AmazonS3 s3;

    static Logger logger = LoggerFactory.getLogger(DownloadContent.class);

    public DownloadContent(String[] args, AmazonS3 s3){
        this.bucketName = Driver.getArgument(args, "s3.bucket.name");
        this.keyName = Driver.getArgument(args, "read.mail") + "/";
        this.contentPath = Driver.getArgument(args, "download.location");
        this.s3 = s3;
    }

    public void downloadObject() {
        System.out.format("Downloading %s from S3 bucket %s...\n", keyName, bucketName);
        try {
            S3Object o = s3.getObject(bucketName, keyName);
            S3ObjectInputStream s3is = o.getObjectContent();
            FileOutputStream fos = new FileOutputStream(new File(contentPath+keyName));
            byte[] read_buf = new byte[1024];
            int read_len = 0;
            while ((read_len = s3is.read(read_buf)) > 0) {
                fos.write(read_buf, 0, read_len);
            }
            s3is.close();
            fos.close();
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getContentPath() {
        return contentPath;
    }

    public void setContentPath(String contentPath) {
        this.contentPath = contentPath;
    }

    public AmazonS3 getS3() {
        return s3;
    }

    public void setS3(AmazonS3 s3) {
        this.s3 = s3;
    }
}
