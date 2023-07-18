package org.example;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.Point;

import java.util.Random;

public class RandomPointsGenerator {
    private static final Logger logger = LogManager.getLogger("RandomPointsGenerator");
    private double minX;
    private double maxX;
    private double minY;
    private double maxY;
    private int numPoints;
    private static int cityNumber = 1;

    public RandomPointsGenerator() {

    }

    public RandomPointsGenerator(double minX, double maxX, double minY, double maxY, int numPoints) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.numPoints = numPoints;
    }

    public void setMinX(double minX) {
        this.minX = minX;
        logger.debug("A minimum X has been set.");
    }

    public void setMaxX(double maxX) {
        this.maxX = maxX;
        logger.debug("A maximum X has been set.");
    }

    public void setMinY(double minY) {
        this.minY = minY;
        logger.debug("A minimum Y has been set.");
    }

    public void setMaxY(double maxY) {
        this.maxY = maxY;
        logger.debug("A maximum Y has been set.");
    }

    public int getNumPoints() {
        logger.debug("A number of points has been retrieved.");
        return numPoints;
    }

    public void setNumPoints(int numPoints) {
        this.numPoints = numPoints;
        logger.debug("A number of points has been set.");
    }

    public Point createRandomPoint() {
        Random random = new Random();
        double x = minX + (maxX - minX) * random.nextDouble();
        double y = minY + (maxY - minY) * random.nextDouble();
        logger.debug("A random point has been created.");
        return new Point(x, y, "City" + cityNumber++);
    }

    public static List<Point> getRandomPoints(List<Point> allPoints, int count) {
        List<Point> randomPoints = new ArrayList<>();
        Random random = new Random();

        int totalPoints = allPoints.size();
        if (totalPoints > 0) {
            for (int i = 0; i < count; i++) {
                int randomIndex = random.nextInt(totalPoints);
                Point randomPoint = allPoints.get(randomIndex);
                randomPoints.add(randomPoint);
            }
        }

        return randomPoints;
    }

    public static double calculateDistance(Point p1, Point p2) {
        double deltaX = p2.getXCoordinate() - p1.getXCoordinate();
        double deltaY = p2.getYCoordinate() - p1.getYCoordinate();
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
}