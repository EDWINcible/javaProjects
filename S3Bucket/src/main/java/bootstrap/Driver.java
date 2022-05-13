package bootstrap;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import domain.DownloadContent;
import domain.SendContent;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.EnhancedPatternLayout;
import org.apache.log4j.Level;
import org.apache.log4j.Priority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Driver {
    static Logger logger = LoggerFactory.getLogger(Driver.class);

    /**
     * @param args --log.file.path executionLogs --log.level INFO --s3.bucket.name cloud-academy-s3-bucket --to Farhan --mail.attachment Edwin.txt --read.mail Edwin --download.packages false download.location src/main/resources
     * @param args --log.file.path /home/edwin/Documents/workspace/S3Bucket/executionLogs --log.level INFO --s3.bucket.name cloud-academy-s3-bucket --to Farhan --mail.attachment /home/edwin/Documents/workspace/S3Bucket/Edwin.txt --read.mail Edwin --download.packages true download.location /home/edwin/Documents/workspace/S3Bucket/src/main/resources/
     * @throws Exception 0 - log.file.path
     *                   1 - log.level
     *                   2 - s3.bucket.name
     *                   3 - to
     *                   4 - mail.attachment
     *                   5 - read.mail
     *                   6 - download.package
     *                   7 - download.location
     */
    public static void main(String[] args) throws Exception {
        configureLogging(Driver.getArgument(args, "log.file.path"),
                Driver.getArgument(args, "log.level"));
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.AP_SOUTH_1).build();
        String downloadPack = getArgument(args, "download.packages");
        if (downloadPack.equals("false")) {
            SendContent sc = new SendContent(args, s3);
            sc.listBuckets();
            sc.sendMail();
            sc.listObjects();
        }
        else if(downloadPack.equals("true")){
            DownloadContent dc = new DownloadContent(args, s3);
            dc.downloadObject();
        }

    }

    public static String configureLogging(String logFile, String logLevel) {
        DailyRollingFileAppender dailyRollingFileAppender = new DailyRollingFileAppender();

        String logFilename = logFile + "/s3Bucket.log";
        switch (logLevel) {
            case "DEBUG": {
                dailyRollingFileAppender.setThreshold(Level.toLevel(Priority.DEBUG_INT));
            }
            case "WARN": {
                dailyRollingFileAppender.setThreshold(Level.toLevel(Priority.WARN_INT));
            }
            case "ERROR": {
                dailyRollingFileAppender.setThreshold(Level.toLevel(Priority.ERROR_INT));
            }
            default: {
                dailyRollingFileAppender.setThreshold(Level.toLevel(Priority.INFO_INT));
            }
            break;
        }

        System.out.println("Log files written out at " + logFilename);
        dailyRollingFileAppender.setFile(logFilename);
        dailyRollingFileAppender.setLayout(new EnhancedPatternLayout("%d [%t] %-5p %c - %m%n"));

        dailyRollingFileAppender.activateOptions();
        org.apache.log4j.Logger.getRootLogger().addAppender(dailyRollingFileAppender);
        return dailyRollingFileAppender.getFile();
    }

    public static String getArgument(String[] args, String argumentName) throws ArrayIndexOutOfBoundsException, NumberFormatException{
        try {
            for (int i = 0; i < args.length; i++) {
                String arg = args[i].replaceAll("--", "");
                if (arg.equals(argumentName)) {
                    return args[i + 1];
                }
                i++;
            }
            return null;
        }
        catch (NumberFormatException e1){
            System.out.println("Enter correct args values");
            return null;
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Enter the last argument value");
            return null;
        }
    }
}
