package domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Kmeans {
    static Logger logger = LoggerFactory.getLogger(Kmeans.class);
    public static List<Point3D> initializeCentroids(int k, List<Point3D> points){
        Collections.shuffle(points);
        List<Point3D> centroids = new ArrayList<>(k);
        for(int i=0; i<k; i++){
            centroids.add(points.get(i));
            logger.info("Centroids : "+i);
        }
        return centroids;
    }

    public static List<Point3D> getNewCenters(List<Point3D> dataset, List<Point3D> centers) {
        List<List<Point3D>> clusters = new ArrayList<>(centers.size());
        for (int i = 0; i < centers.size(); i++) {
            clusters.add(new ArrayList<Point3D>());
        }
        for (Point3D data : dataset) {
            int index = data.getNearestPointIndex(centers);
            clusters.get(index).add(data);
            logger.info("Point "+data.getX()+","+data.getY()+","+data.getZ()+" assigned to cluster no. :"+index);
        }
        List<Point3D> newCenters = new ArrayList<>(centers.size());
        for (List<Point3D> cluster : clusters) {
            Point3D newCentroid = Point3D.getMean(cluster);
            newCenters.add(Point3D.getMean(cluster));
            logger.info("New centroids :"+newCentroid.getX()+","+newCentroid.getY()+","+newCentroid.getZ());
        }
        return newCenters;
    }

    public static double getDistance(List<Point3D> oldCenters, List<Point3D> newCenters) {
        double accumDist = 0;
        for (int i = 0; i < oldCenters.size(); i++) {
            double dist = oldCenters.get(i).getDistance(newCenters.get(i));
            accumDist += dist;
        }
        return accumDist;
    }

    public static List<Point3D> kmeans(List<Point3D> centers, List<Point3D> dataset, int k) {
        boolean converged;
        do {
            List<Point3D> newCenters = getNewCenters(dataset, centers);
            double dist = getDistance(centers, newCenters);
            logger.info("Distance between old centers and new centers :"+dist);
            centers = newCenters;
            converged = dist == 0;
        } while (!converged);
        return centers;
    }
}
