package Classification;

import java.util.ArrayList;
import java.util.List;

public class Model {
    static List<double[]> red = new ArrayList<>();
    static List<double[]> green = new ArrayList<>();
    static List<double[]> blue = new ArrayList<>();
    StatsSignature stats = new StatsSignature();

    public static void classify(List<double[]> trainData){
        for(double[] d: trainData){
            if(d[3]==0){
                red.add(d);
            }
            else if(d[3]==1){
                green.add(d);
            }
            else if(d[3]==2){
                blue.add(d);
            }
            else{
                System.out.println("errer");
            }
        }
    }

    public static List<double[]> redClass(){
        return red;
    }

    public static List<double[]> greenClass(){
        return green;
    }
    public static List<double[]> blueClass(){
        return blue;
    }

}
