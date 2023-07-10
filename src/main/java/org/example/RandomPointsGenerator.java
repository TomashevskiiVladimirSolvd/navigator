package org.example;

import org.example.model.Point;

import java.util.Random;

public class RandomPointsGenerator {
    private double minX;
    private double maxX;
    private double minY;
    private double maxY;
    private int numPoints;

    public RandomPointsGenerator(double minX, double maxX, double minY, double maxY, int numPoints) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.numPoints = numPoints;
    }

    public void setMinX(double minX) {
        this.minX = minX;
    }

    public void setMaxX(double maxX) {
        this.maxX = maxX;
    }

    public void setMinY(double minY) {
        this.minY = minY;
    }

    public void setMaxY(double maxY) {
        this.maxY = maxY;
    }

    public int getNumPoints() {
        return numPoints;
    }

    public void setNumPoints(int numPoints) {
        this.numPoints = numPoints;
    }

    public Point createRandomPoint() {
        Random random = new Random();
        double x = minX + (maxX - minX) * random.nextDouble();
        double y = minY + (maxY - minY) * random.nextDouble();
        return Point.builder()
                .setXCoordinate(x)
                .setYCoordinate(y)
                .build();
    }
}
