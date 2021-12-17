package Bootstrap;

import Classification.*;
import org.apache.log4j.*;
import org.slf4j.LoggerFactory;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class Driver {
    static Properties applicationConfig = new Properties();

//    static Logger logger = (Logger) LoggerFactory.getLogger(Driver.class);

    /**
     * @param args
     * --log.file.path - path to log directory.
     * --debug.log t/f
     * --file.name - csv file
     *
     */

    public static void main(String[] args){
        List<double[]> dataList = new ArrayList<>();
        List<double[]> trainData = new ArrayList<>();
        List<double[]> testData = new ArrayList<>();
        List<double[]> redList = new ArrayList<>();
        List<double[]> greenList = new ArrayList<>();
        List<double[]> blueList = new ArrayList<>();
//        StatsSignature stat = new StatsSignature();
//        double[] meanArr = new double[3];
        double accuracy = 0;

        HashMap<Integer, StatsSignature> stats = new HashMap<>();
        initializeProperties(args);
        configureLogging(extractProperty("log.file.path"),
                Boolean.parseBoolean(extractProperty("debug.log")));
//        String fileName = extractProperty("file.name");
        generateData.createData();
        dataList = generateData.createDataList();
        trainData = sortData.trainingData(dataList);
        testData = sortData.testData();
        Model.classify(trainData);
        redList= Model.redClass();
        greenList= Model.greenClass();
        blueList= Model.blueClass();
        stats = MeanAndSD.statsMaker(redList,greenList,blueList);
        accuracy = Validation.accuracyPer(testData,stats);
        System.out.print(accuracy);
//        logger.info(accuracy);
    }

    public static String configureLogging(String logDirectory, boolean debug) {
        DailyRollingFileAppender dailyRollingFileAppender = new DailyRollingFileAppender();

        String logFileName = "";
        if (!debug) {
            dailyRollingFileAppender.setThreshold(Level.toLevel(Priority.INFO_INT));
            logFileName = logDirectory + "rgb.log";

        } else {
            dailyRollingFileAppender.setThreshold(Level.toLevel(Priority.DEBUG_INT));
            logFileName = logDirectory + "rgb-debug.log";
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
