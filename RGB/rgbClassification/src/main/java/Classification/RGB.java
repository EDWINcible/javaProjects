package Classification;

import java.util.concurrent.ThreadLocalRandom;

public class RGB {
    private int colourId;
    private String colour;
    private int R;
    private int G;
    private int B;


    public RGB(int id){
        if(id == 0){
            this.colour = "Red";
            this.colourId=id;
            this.R=ThreadLocalRandom.current().nextInt(200, 256);
            this.G=ThreadLocalRandom.current().nextInt(0, 51);
            this.B=ThreadLocalRandom.current().nextInt(0, 51);
        }
        else if(id == 1){
            this.colour = "Green";
            this.colourId=id;
            this.R=ThreadLocalRandom.current().nextInt(0, 51);
            this.G=ThreadLocalRandom.current().nextInt(200, 256);
            this.B=ThreadLocalRandom.current().nextInt(0, 51);
        }
        else if(id == 2){
            this.colour = "Blue";
            this.colourId=id;
            this.R=ThreadLocalRandom.current().nextInt(0, 51);
            this.G=ThreadLocalRandom.current().nextInt(0, 51);
            this.B=ThreadLocalRandom.current().nextInt(200, 256);
        }
        else{
            System.out.println("wrong id");
        }
    }

    public int getColourId() {
        return colourId;
    }

    public void setColourId(int colourId) {
        this.colourId = colourId;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public int getR() {
        return R;
    }

    public void setR(int r) {
        R = r;
    }

    public int getG() {
        return G;
    }

    public void setG(int g) {
        G = g;
    }

    public int getB() {
        return B;
    }

    public void setB(int b) {
        B = b;
    }
}
