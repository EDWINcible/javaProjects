package domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Generate {
    static Logger logger = LoggerFactory.getLogger(Generate.class);
    public static ArrayList<Snakes> createSnakes() {
        ArrayList<Snakes> snakesList = new ArrayList<Snakes>();
        Snakes snake1 = (new snakeBuilder())
                .withStartPoint(99)
                .withEndPoint(4)
                .build();
        Snakes snake2 = (new snakeBuilder())
                .withStartPoint(31)
                .withEndPoint(14)
                .build();
        Snakes snake3 = (new snakeBuilder())
                .withStartPoint(59)
                .withEndPoint(37)
                .build();
        Snakes snake4 = (new snakeBuilder())
                .withStartPoint(91)
                .withEndPoint(74)
                .build();
        snakesList.add(snake1);
        snakesList.add(snake2);
        snakesList.add(snake3);
        snakesList.add(snake4);
        logger.info("Snakes created");
        return snakesList;
    }

    public static ArrayList<Ladders> createLadders() {
        ArrayList<Ladders> laddersList = new ArrayList<Ladders>();
        Ladders ladder1 = (new ladderBuilder())
                .withStartPoint(2)
                .withEndPoint(23)
                .build();
        Ladders ladder2 = (new ladderBuilder())
                .withStartPoint(29)
                .withEndPoint(54)
                .build();
        Ladders ladder3 = (new ladderBuilder())
                .withStartPoint(39)
                .withEndPoint(80)
                .build();
        Ladders ladder4 = (new ladderBuilder())
                .withStartPoint(75)
                .withEndPoint(96)
                .build();
        laddersList.add(ladder1);
        laddersList.add(ladder2);
        laddersList.add(ladder3);
        laddersList.add(ladder4);
        logger.info("Ladders created");
        return laddersList;
    }

}
