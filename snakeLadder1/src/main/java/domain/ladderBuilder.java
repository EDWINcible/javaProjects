package domain;

public class ladderBuilder {
    private int startPoint;
    private int endPoint;
    static Ladders ladder;

    public ladderBuilder withStartPoint(int startPoint){
        this.startPoint=startPoint;
        return this;
    }

    public ladderBuilder withEndPoint(int endPoint){
        this.endPoint=endPoint;
        return this;
    }

    public int getStartPoint() {
        return startPoint;
    }

    public int getEndPoint() {
        return endPoint;
    }

    public Ladders build() {
        return new Ladders(this);
    }
}
