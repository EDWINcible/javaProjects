package domain;

public class Ladders {
    private int startPoint;
    private int endPoint;

    public Ladders(ladderBuilder ladderBuilder) {
        this.startPoint=ladderBuilder.getStartPoint();
        this.endPoint=ladderBuilder.getEndPoint();
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
