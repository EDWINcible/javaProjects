package domain;

import bootstrap.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

import static domain.playerBuilder.player;

public class Board implements Callable<Players> {
    static Logger logger = LoggerFactory.getLogger(Driver.class);
    private Players player;
    private ArrayList<Snakes> snakesList;
    private ArrayList<Ladders> laddersList;

    public Board(Players player, ArrayList<Snakes> snakesList, ArrayList<Ladders> laddersList) {
        this.player=player;
        this.snakesList=snakesList;
        this.laddersList=laddersList;
    }


    @Override
    public Players call() throws Exception {
        Thread.currentThread().setName(player.getName());
        logger.info("Thread :"+Thread.currentThread().getName());
            while (Driver.flag1 == true) {
                int dice = ThreadLocalRandom.current().nextInt(1, 7);
                int pos = player.getCurrPosition();
                pos = pos + dice;
                if (pos < 100) {
                    for (Snakes snake : snakesList) {
                        if (pos == snake.getStartPoint()) {
                            pos = snake.getEndPoint();
                            logger.info(player.getName()+" Oops! snake bit you. You are at " + pos);
                        }
                    }
                    for (Ladders ladder : laddersList) {
                        if (pos == ladder.getStartPoint()) {
                            pos = ladder.getEndPoint();
                            logger.info(player.getName()+" Yay! You found a ladder. You are at " + pos);
                        }
                    }
                }
                else if(pos == 100){
                    logger.info(player.getName() + " wins!!!");
                    Driver.flag1=false;
                }
                else {
                    logger.info(player.getName()+" you need less than " + dice);
                    pos = pos - dice;
                }
                player.setCurrPosition(pos);
                logger.info(player.getName() + " is at " + player.getCurrPosition());
                Thread.sleep(5);
            }
            return player;
        }
}
