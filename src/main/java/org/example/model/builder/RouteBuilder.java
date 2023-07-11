package org.example.model.builder;

import org.example.model.Point;
import org.example.model.Route;

import java.util.List;

public class RouteBuilder {

    private Integer id;
    private Point startPoint;
    private Point endPoint;
    private long distance;
    private List<Point> wayPoints;

    public RouteBuilder(){

    }

    public RouteBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public RouteBuilder setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
        return this;
    }

    public RouteBuilder setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
        return this;
    }

    public RouteBuilder setDistance(long distance) {
        this.distance = distance;
        return this;
    }
    public RouteBuilder setWayPoints(List<Point> wayPoints) {
        this.wayPoints = wayPoints;
        return this;
    }

    public Route getRoute() {
        return new Route(startPoint, endPoint, distance, wayPoints);
    }
}
