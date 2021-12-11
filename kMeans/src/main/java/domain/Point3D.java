package domain;

import java.util.List;

public class Point3D {
    private double x;
    private double y;
    private double z;

    public Point3D(double x, double y, double z){
        this.x=x;
        this.y=y;
        this.z=z;
    }

    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    double getDistance(Point3D other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2)
                + Math.pow(this.y - other.y, 2)+ Math.pow(this.z - other.z, 2));
    }

    public int getNearestPointIndex(List<Point3D> points) {
        int index = -1;
        double minDist = Double.MAX_VALUE;
        for (int i = 0; i < points.size(); i++) {
            double dist = this.getDistance(points.get(i));
            if (dist < minDist) {
                minDist = dist;
                index = i;
            }
        }
        return index;
    }

    public static Point3D getMean(List<Point3D> points) {
        float accumX = 0;
        float accumY = 0;
        float accumZ = 0;
        if (points.size() == 0) return new Point3D(accumX, accumY, accumZ);
        for (Point3D point : points) {
            accumX += point.x;
            accumY += point.y;
            accumZ += point.z;
        }
        return new Point3D(accumX / points.size(), accumY / points.size(),accumZ / points.size() );
    }
}
