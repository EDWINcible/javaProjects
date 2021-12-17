package Classification;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class sortData {
    static List<double[]> trainData = new ArrayList<>();
    static List<double[]> testData = new ArrayList<>();
    static HashSet<Integer> index = new HashSet<>();
    public static List<double[]> trainingData(List<double[]> dataList){
        while(index.size()<(0.7*dataList.size())){
            int i = ThreadLocalRandom.current().nextInt(0, dataList.size()+1);
            index.add(i);
        }
        for (int i = 0; i < dataList.size(); i++) {
            if (index.contains(i)) {
                trainData.add(dataList.get(i));
            } else {
                testData.add(dataList.get(i));
            }
        }
        return trainData;
    }

    public static List<double[]> testData(){
        return testData;
    }
}
