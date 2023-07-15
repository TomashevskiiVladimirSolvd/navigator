package org.example.model.builder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.Point;
import org.example.model.Route;

import java.util.List;

public class RouteBuilder {

    private static final Logger logger = LogManager.getLogger("RouteBuilder");
    private Integer id;
    private Point startPoint;
    private Point endPoint;
    private long distance;
    private List<Point> wayPoints;

    public RouteBuilder() {

    }

    public RouteBuilder setId(Integer id) {
        this.id = id;
        logger.debug("An id has been set for the route.");
        return this;
    }

    public RouteBuilder setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
        logger.debug("A start point has been set for the route.");
        return this;
    }

    public RouteBuilder setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
        logger.debug("An endpoint has been set for the route.");
        return this;
    }

    public RouteBuilder setDistance(long distance) {
        this.distance = distance;
        logger.debug("A distance has been set for the route.");
        return this;
    }
    public RouteBuilder setWayPoints(List<Point> wayPoints) {
        this.wayPoints = wayPoints;
        logger.debug("Waypoints have been set for the route.");
        return this;
    }

    public Route getRoute() {
        return new Route(startPoint, endPoint, distance);
    }
}
