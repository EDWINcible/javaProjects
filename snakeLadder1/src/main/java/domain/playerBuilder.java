package domain;

public class playerBuilder {
    private String name;
    private int currPosition;
    static Players player;

    public playerBuilder withName(String name){
        this.name=name;
        return this;
    }

    public playerBuilder withCurrPosition(int currPosition){
        this.currPosition=currPosition;
        return this;
    }

    public String getName() {
        return name;
    }

    public int getCurrPosition() {
        return currPosition;
    }

    public static Players getPlayer() {
        return player;
    }

    public Players build() {
        return new Players(this);
    }
}
