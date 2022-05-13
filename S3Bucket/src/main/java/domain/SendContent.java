package domain;

import bootstrap.Driver;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

public class SendContent {
    private String bucketName;
    private String keyName;
    private String contentPath;
    private AmazonS3 s3;

    static Logger logger = LoggerFactory.getLogger(SendContent.class);

    public SendContent(String[] args, AmazonS3 s3){
        this.bucketName = Driver.getArgument(args, "s3.bucket.name");
        this.keyName = Driver.getArgument(args, "to");
        this.contentPath = Driver.getArgument(args, "mail.attachment");
        this.s3 = s3;
    }

    public Bucket getBucket() {
        Bucket named_bucket = null;
        List<Bucket> buckets = getS3().listBuckets();
        for (Bucket b : buckets) {
            if (b.getName().equals(bucketName)) {
                named_bucket = b;
            }
        }
        return named_bucket;
    }

    public void listBuckets() {
        Bucket named_bucket = null;
        List<Bucket> buckets = s3.listBuckets();
        for (Bucket b : buckets) {
            logger.info(b.getName());
        }
    }

    public S3ObjectSummary getObject(){
        S3ObjectSummary key = null;
        try {
            ListObjectsV2Result result = s3.listObjectsV2(getBucketName());
            List<S3ObjectSummary> objects = result.getObjectSummaries();
            for (S3ObjectSummary os : objects) {
                if (os.toString().equals(getKeyName())) {
                    key = os;
                }
            }
            return key;
        }
        catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
        }
        return key;
    }

    public void listObjects(){

        ListObjectsV2Result result = s3.listObjectsV2(bucketName);
        List<S3ObjectSummary> objects = result.getObjectSummaries();
        for (S3ObjectSummary os : objects) {
            logger.info(os.toString());
        }
    }

    public void sendMail(){
        try {
            s3.putObject(bucketName, getKeyName()+"/", new File(contentPath));
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
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
