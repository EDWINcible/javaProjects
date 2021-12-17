package Classification;

import java.util.HashMap;
import java.util.List;

public class MeanAndSD {
    static HashMap<Integer,StatsSignature> stats = new HashMap<>();
    public static HashMap<Integer,StatsSignature> statsMaker(List<double[]> redList, List<double[]> greenList, List<double[]> blueList){

        for(int i=0; i<3; i++){
            StatsSignature stat = new StatsSignature();
            if(i==0){
                stat.setMean(mean(redList));
                double[] mean = stat.getMean();
                stat.setSD(sd(redList,mean));
                stats.put(i,stat);
            }
            else if(i==1){
                stat.setMean(mean(greenList));
                double[] mean1 = stat.getMean();
                stat.setSD(sd(greenList,mean1));
                stats.put(i,stat);
            }
            else {
                stat.setMean(mean(blueList));
                double[] mean2 = stat.getMean();
                stat.setSD(sd(blueList,mean2));
                stats.put(i,stat);
            }
        }
        return stats;
    }

    public static double[] mean(List<double[]> colourClass){
        double[] meansColumn = new double[3];
        for(int i=0;i<3;i++){
            double sum = 0;
            for(double[] d: colourClass ){
                sum+=d[i];
            }

            meansColumn[i] = sum/colourClass.size();
            System.out.println("index "+i+" MEAN :"+meansColumn[i]);
        }
        return meansColumn;
    }

    public static double[] sd(List<double[]> colourClass, double[] mean){
        double[] sd = new double[3];
        for (int i = 0; i < 3; i++) {
            double squaresum = 0;
            for (double[] d : colourClass) {
                squaresum = squaresum + Math.pow((d[i] - mean[i]), 2);
            }
            sd[i] = Math.sqrt((squaresum / colourClass.size()));
            System.out.println("index "+i+" SD :"+sd[i]);
        }
        return sd;
    }
}
