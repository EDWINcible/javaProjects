package domain;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class csvReader {
    public static List<Point3D> getPointsCSV(String path){
        String line = "";
        List<Point3D> points = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null){
                String[] values = line.split(",");
                Point3D point = createPoints(values);
                points.add(point);
//                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return points;
    }

    private static Point3D createPoints(String[] values) {
        double x = Double.parseDouble(values[0]);
        double y = Double.parseDouble(values[1]);
        double z = Double.parseDouble(values[2]);

        return new Point3D(x, y, z);
    }
}
