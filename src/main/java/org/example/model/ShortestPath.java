package org.example.model;

import java.util.Objects;

public class ShortestPath {
    private Integer id;
    private Point previousPoint;
    private long distance;

    public ShortestPath() {
    }

    public ShortestPath(Integer id, Point previousPoint, long distance) {
        this.id = id;
        this.previousPoint = previousPoint;
        this.distance = distance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Point getPreviousPoint() {
        return previousPoint;
    }

    public void setPreviousPoint(Point previousPoint) {
        this.previousPoint = previousPoint;
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
        ShortestPath that = (ShortestPath) o;
        return distance == that.distance && Objects.equals(id, that.id) && Objects.equals(previousPoint, that.previousPoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, previousPoint, distance);
    }

    @Override
    public String toString() {
        return "ShortestPath{" +
                "id=" + id +
                ", previousPoint=" + previousPoint +
                ", distance=" + distance +
                '}';
    }
}
