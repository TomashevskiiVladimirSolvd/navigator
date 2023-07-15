package org.example.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Route {
    private static final Logger logger = LogManager.getLogger("Route");
    private Integer id;
    private Point startPoint;
    private Point endPoint;
    private long distance;

    private List<Point> wayPoints;

    public Route() {
    }

    public Route(Point startPoint, Point endPoint, long distance) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.distance = distance;
    }

    public Route(Point startPoint, Point endPoint, long distance, List<Point> wayPoints) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.distance = distance;
    }

    public Integer getId() {
        logger.debug("The route id has been retrieved.");
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        logger.debug("The route id has been set.");
    }

    public Point getStartPoint() {
        logger.debug("The start point has been retrieved.");
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        logger.debug("The start point has been set.");
        this.startPoint = startPoint;
    }

    public Point getEndPoint() {
        logger.debug("The end point has been retrieved.");
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
        logger.debug("The end point has been set.");
    }

    public long getDistance() {
        logger.debug("The distance has been retrieved.");
        return distance;
    }

    public void setDistance(long distance) {
        logger.debug("The distance has been set.");
        this.distance = distance;
    }

    public List<Point> getWayPoints() {
        logger.debug("The list of waypoints has been retrieved.");
        return wayPoints;
    }

    public void setWayPoints(List<Point> wayPoints) {
        this.wayPoints = wayPoints;
        logger.debug("The list of waypoints has been set.");
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
                '}';
    }
}
