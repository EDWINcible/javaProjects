package bootstrap;

import domain.Implement;
import domain.Inventory;

import java.io.*;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.EnhancedPatternLayout;
import org.apache.log4j.Level;
import org.apache.log4j.Priority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Driver {
    public static Logger logger = LoggerFactory.getLogger(Driver.class);
    public static void main(String[] args) throws Exception {
        Implement implement = new Implement();
        configureLogging("gumballMachine.log","INFO");
        logger.info("Machine Started");
        implement.depositMoney();
        logger.info("Money Deposited");
        implement.buySweets();
        logger.info("Sweets Bought");
        implement.transaction();
        logger.info("transaction Complete");
        logger.info("Machine Stopped");
    }


    public static Object saveSweets(Inventory inventory) throws IOException {
        ObjectOutputStream outSweet = new ObjectOutputStream(new FileOutputStream("inventorySave.dat"));
        outSweet.writeObject(inventory);
        outSweet.close();
        return outSweet;
    }


    public static Object readSweets() throws IOException, ClassNotFoundException {
        ObjectInputStream inSweet = new ObjectInputStream(new FileInputStream("inventorySave.dat"));
        Inventory inventory = (Inventory) inSweet.readObject();
        inSweet.close();
        return inventory;
    }

    public static String configureLogging(String logFile, String logLevel) {
        DailyRollingFileAppender dailyRollingFileAppender = new DailyRollingFileAppender();

        String logFileName = "";
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

        System.out.println("Log files are written out at " + logFile);
        dailyRollingFileAppender.setFile(logFile);
        dailyRollingFileAppender.setLayout(new EnhancedPatternLayout("%d [%t] %-5p %c - %m%n"));

        dailyRollingFileAppender.activateOptions();
        org.apache.log4j.Logger.getRootLogger().addAppender(dailyRollingFileAppender);
        return dailyRollingFileAppender.getFile();
    }

}
