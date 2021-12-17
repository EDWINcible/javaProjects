package domain;

public class Snakes {
    private int startPoint;
    private int endPoint;

    public Snakes(snakeBuilder snakeBuilder) {
        this.startPoint=snakeBuilder.getStartPoint();
        this.endPoint=snakeBuilder.getEndPoint();
    }

    public int getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(int startPoint) {
        this.startPoint = startPoint;
    }

    public int getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(int endPoint) {
        this.endPoint = endPoint;
    }
}
