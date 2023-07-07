package org.example.model;

import java.util.List;
import java.util.Objects;

public class Point {
    private Integer id;
    private double xCoordinate;
    private double yCoordinate;

    public Point() {
    }

    public Point(Integer id, double xCoordinate, double yCoordinate) {
        this.id = id;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public double getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Double.compare(point.xCoordinate, xCoordinate) == 0 && Double.compare(point.yCoordinate, yCoordinate) == 0 && Objects.equals(id, point.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, xCoordinate, yCoordinate);
    }

    @Override
    public String toString() {
        return "Point{" +
                "id=" + id +
                ", xCoordinate=" + xCoordinate +
                ", yCoordinate=" + yCoordinate +
                '}';
    }
}
