package org.example.model;

import java.util.List;
import java.util.Objects;

public class Route {
    private Integer id;
    private Point startPoint;
    private Point endPoint;
    private long distance;

    private List<Point> wayPoints;

    public Route() {
    }

    public Route(Point startPoint, Point endPoint, long distance, List<Point> wayPoints) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.distance = distance;
        this.wayPoints = wayPoints;
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

    public List<Point> getWayPoints() {
        return wayPoints;
    }

    public void setWayPoints(List<Point> wayPoints) {
        this.wayPoints = wayPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Route)) return false;

        Route route = (Route) o;

        if (getDistance() != route.getDistance()) return false;
        if (!getId().equals(route.getId())) return false;
        if (getStartPoint() != null ? !getStartPoint().equals(route.getStartPoint()) : route.getStartPoint() != null)
            return false;
        if (getEndPoint() != null ? !getEndPoint().equals(route.getEndPoint()) : route.getEndPoint() != null)
            return false;
        return getWayPoints() != null ? getWayPoints().equals(route.getWayPoints()) : route.getWayPoints() == null;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (getStartPoint() != null ? getStartPoint().hashCode() : 0);
        result = 31 * result + (getEndPoint() != null ? getEndPoint().hashCode() : 0);
        result = 31 * result + (int) (getDistance() ^ (getDistance() >>> 32));
        result = 31 * result + (getWayPoints() != null ? getWayPoints().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", startPoint=" + startPoint +
                ", endPoint=" + endPoint +
                ", distance=" + distance +
                ", wayPoints=" + wayPoints +
                '}';
    }
}
