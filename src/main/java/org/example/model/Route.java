package org.example.model;

import java.util.Objects;

public class Route {
    private Integer id;
    private Point startPoint;
    private Point endPoint;
    private long distance;

    public Route() {
    }

    public Route(Point startPoint, Point endPoint, long distance) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.distance = distance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return distance == route.distance && Objects.equals(startPoint, route.startPoint) && Objects.equals(endPoint, route.endPoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startPoint, endPoint, distance);
    }

    @Override
    public String toString() {
        return "Route{" +
                "startPoint=" + startPoint +
                ", endPoint=" + endPoint +
                ", shortestDistance=" + distance +
                '}';
    }
}
