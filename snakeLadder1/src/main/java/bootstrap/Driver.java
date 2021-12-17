package bootstrap;

import domain.*;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.EnhancedPatternLayout;
import org.apache.log4j.Level;
import org.apache.log4j.Priority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class Driver {
    static Logger logger = LoggerFactory.getLogger(Driver.class);
    public static boolean flag1=true;
    public static void main(String[] args) {
        configureLogging(args[0]+"snakeLadderThread.log", "INFO");
        while(true) {
            ArrayList<Players> playerList = new ArrayList<Players>();
            ArrayList<Snakes> snakesList = new ArrayList<Snakes>();
            ArrayList<Ladders> laddersList = new ArrayList<Ladders>();
            List<Board> boardList = new ArrayList<Board>();
            List<Future<Players>> futureList = null;
            List<Players> resultList = new ArrayList<Players>();

            Board thread1 = new Board((new playerBuilder())
                    .withName("Edwin")
                    .withCurrPosition(0)
                    .build(), snakesList, laddersList);
            Board thread2 = new Board((new playerBuilder())
                    .withName("Pratik")
                    .withCurrPosition(0)
                    .build(), snakesList, laddersList);
            Board thread3 = new Board((new playerBuilder())
                    .withName("Chris")
                    .withCurrPosition(0)
                    .build(), snakesList, laddersList);
            Board thread4 = new Board((new playerBuilder())
                    .withName("Trevor")
                    .withCurrPosition(0)
                    .build(), snakesList, laddersList);
            boardList.add(thread1);
            boardList.add(thread2);
            boardList.add(thread3);
            boardList.add(thread4);

            snakesList = Generate.createSnakes();
            laddersList = Generate.createLadders();
            logger.info("Game started");

            ExecutorService service = Executors.newFixedThreadPool(4);
            try {
                futureList = service.invokeAll(boardList);
                for (int i = 0; i < 4; i++) {
                    Players player = (Players) futureList.get(i).get();
//                if(player.getCurrPosition()==100)
//                    flag1=false;
//                    service.shutdown();
//                try {
//                    if (!service.awaitTermination(800, TimeUnit.MILLISECONDS)) {
//                        service.shutdownNow();
//                    }
//                } catch (InterruptedException e) {
//                    service.shutdownNow();
//                }
                    logger.info(player.getName() + player.getCurrPosition());
                }
                for (Players player : resultList) {
                    logger.info(player.getName() + " " + player.getCurrPosition());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            logger.info("Game Ended");
            flag1=true;
        }
    }

    public static String configureLogging(String logFile, String logLevel) {
        DailyRollingFileAppender dailyRollingFileAppender = new DailyRollingFileAppender();

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
