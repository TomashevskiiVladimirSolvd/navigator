package org.example.model.builder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.Point;

public class PointBuilder {
    private static final Logger logger = LogManager.getLogger("PointBuilder");
    private Integer id;
    private double xCoordinate;
    private double yCoordinate;
    private String cityName;

    public PointBuilder() {

    }

    public PointBuilder setId(Integer id) {
        this.id = id;
        logger.debug("An id has been set for the point.");
        return this;
    }

    public PointBuilder setXCoordinate(double xCoordinate) {
        this.xCoordinate = xCoordinate;
        logger.debug("An X coordinate has been set for the point.");
        return this;
    }

    public PointBuilder setYCoordinate(double yCoordinate) {
        this.yCoordinate = yCoordinate;
        logger.debug("A Y coordinate has been set for the point.");
        return this;
    }

    public PointBuilder setCityName(String cityName) {
        this.cityName = cityName;
        logger.debug("A city has been set for the point.");
        return this;
    }

    public Point getPoint() {
        logger.debug("A new point has been retrieved.");
        return new Point(xCoordinate, yCoordinate, cityName);
    }

}