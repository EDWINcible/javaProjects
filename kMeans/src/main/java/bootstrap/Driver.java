package bootstrap;

import domain.*;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.EnhancedPatternLayout;
import org.apache.log4j.Level;
import org.apache.log4j.Priority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Driver {
    static Properties applicationConfig = new Properties();
    static Logger logger = LoggerFactory.getLogger(Driver.class);

    /**
     * @param args
     * --log.file.path - path to log directory.
     * --debug.log t/f
     * --csv.file.path - path to csv
     * --total.clusters - int
     */

    public static void main(String[] args){
        initializeProperties(args);
        configureLogging(extractProperty("log.file.path"),
                Boolean.parseBoolean(extractProperty("debug.log")));
        List<Point3D> points = new ArrayList<>();
        List<Point3D> centers = new ArrayList<>();

        String path = extractProperty("csv.file.path");
        int k = Integer.parseInt(extractProperty("total.clusters"));
        points = csvReader.getPointsCSV(path);
        List<Point3D> randomCentroids = Kmeans.initializeCentroids(k,points);
        centers = Kmeans.kmeans(randomCentroids, points, k);
        for(Point3D c: centers){
            logger.info("After convergence centers :"+c.getX()+","+c.getY()+","+c.getZ());
        }

    }

    public static String configureLogging(String logDirectory, boolean debug) {
        DailyRollingFileAppender dailyRollingFileAppender = new DailyRollingFileAppender();

        String logFileName = "";
        if (!debug) {
            dailyRollingFileAppender.setThreshold(Level.toLevel(Priority.INFO_INT));
            logFileName = logDirectory + "kmeans.log";

        } else {
            dailyRollingFileAppender.setThreshold(Level.toLevel(Priority.DEBUG_INT));
            logFileName = logDirectory + "kmeans-debug.log";
        }

        System.out.println("Log files written out at " + logFileName);
        dailyRollingFileAppender.setFile(logFileName);
        dailyRollingFileAppender.setLayout(new EnhancedPatternLayout("%-6d [%t] %-5p %c - %m%n"));

        dailyRollingFileAppender.activateOptions();
        org.apache.log4j.Logger.getRootLogger().addAppender(dailyRollingFileAppender);
        return dailyRollingFileAppender.getFile();
    }

    public static void initializeProperties(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].contains("--")) {
                String configParam = args[i].replaceAll("--", "");
                applicationConfig.put(configParam, args[i + 1]);
            }
        }
    }

    public static String extractProperty(String propertyName) {
        return (String) applicationConfig.get(propertyName);
    }
}
