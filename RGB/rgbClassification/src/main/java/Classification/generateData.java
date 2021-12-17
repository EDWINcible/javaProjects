package Classification;

import java.util.ArrayList;
import java.util.List;

public class generateData {
    static List<RGB> colourList = new ArrayList<>();
    static List<double[]> content = new ArrayList<>();
    public static List<double[]> createDataList(){
        for(RGB i: colourList){
            double[] data = {Double.valueOf(i.getR()), Double.valueOf(i.getG()), Double.valueOf(i.getB()), Double.valueOf(i.getColourId())};
            content.add(data);
        }
        return  content;
    }

    public static void createData(){
        for(int i=0;i<50;i++){
            RGB r = new RGB(0);
            RGB g = new RGB(1);
            RGB b = new RGB(2);
            colourList.add(r);
            colourList.add(g);
            colourList.add(b);
        }
    }
}
