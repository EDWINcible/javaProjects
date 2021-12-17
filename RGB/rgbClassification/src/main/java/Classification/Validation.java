package Classification;

import Bootstrap.Driver;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;

public class Validation {
//    static Logger logger = (Logger) LoggerFactory.getLogger(Validation.class);
    public static int PDF(double[] sampleRow, HashMap<Integer, StatsSignature> stats) {
        double[] pdfValues = new double[3];
        double max = 0;
        int index = 0;
        for (int i = 0; i < 3; i++) {
            double distance = 0.0d;
            for (int j = 0; j < 3; j++) {
                double tempSD = stats.get(i).getSD()[j];
                double tempMean = stats.get(i).getMean()[j];
                double constant = 1.0d / (Math.sqrt(2 * Math.PI * (Math.pow(tempSD, 2))));
                double term = (-1.0d) * Math.pow(sampleRow[j] - tempMean, 2);
                term = term / (2 * (Math.pow(tempSD, 2)));
                distance = distance + constant * Math.exp(term);
            }
            pdfValues[i] = distance;
            if (max < distance) {
                max = distance;
                index = i;
            }
        }
        System.out.println("PDF VALUES: " + pdfValues[0] + " " + pdfValues[1] + " " + pdfValues[2] + "   Predicted index: " + index);
        return index;
    }

    public static double accuracyPer(List<double[]> testData, HashMap<Integer, StatsSignature> stats) {
        int accuracy = 0;
        for (double[] d : testData) {
            int colorDigit = PDF(d, stats);
            if (colorDigit == d[3]) {
                accuracy++;
            }
        }

        return (double) (accuracy / testData.size()) * 100d;
    }
    
}
