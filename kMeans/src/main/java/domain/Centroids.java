package domain;

import java.util.concurrent.ThreadLocalRandom;

public class Centroids {
    private double Cx;
    private double Cy;
    private double Cz;

    public Centroids(){
        this.Cx = ThreadLocalRandom.current().nextInt(0, 100);
        this.Cy = ThreadLocalRandom.current().nextInt(0, 100);
        this.Cz = ThreadLocalRandom.current().nextInt(0, 100);
    }

    public double getCx() {
        return Cx;
    }

    public void setCx(double cx) {
        Cx = cx;
    }

    public double getCy() {
        return Cy;
    }

    public void setCy(double cy) {
        Cy = cy;
    }

    public double getCz() {
        return Cz;
    }

    public void setCz(double cz) {
        Cz = cz;
    }
}
