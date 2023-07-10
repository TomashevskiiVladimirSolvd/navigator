package org.example.builder;

import org.example.model.Point;
import org.example.model.Route;

public class RouteBuilder {
    private Integer id;
    private Point startPoint;
    private Point endPoint;
    private long distance;

    public RouteBuilder() {
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

    public Route build() {
        Route route = new Route();
        route.setId(this.id);
        route.setStartPoint(this.startPoint);
        route.setEndPoint(this.endPoint);
        route.setDistance(this.distance);
        return route;
    }
}
