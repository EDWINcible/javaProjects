package domain;

public class snakeBuilder {
    private int startPoint;
    private int endPoint;
    static Snakes snake;

    public snakeBuilder withStartPoint(int startPoint){
        this.startPoint=startPoint;
        return this;
    }

    public snakeBuilder withEndPoint(int endPoint){
        this.endPoint=endPoint;
        return this;
    }

    public int getStartPoint() {
        return startPoint;
    }

    public int getEndPoint() {
        return endPoint;
    }

    public Snakes build(){return new Snakes(this);}
}
