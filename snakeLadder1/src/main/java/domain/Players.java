package domain;

import java.io.Serializable;

public class Players implements Serializable {
    private String name;
    private int currPosition;

    public Players(playerBuilder playerBuilder) {
        this.name = playerBuilder.getName();
        this.currPosition = playerBuilder.getCurrPosition();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrPosition() {
        return currPosition;
    }

    public void setCurrPosition(int currPosition) {
        this.currPosition = currPosition;
    }

}
