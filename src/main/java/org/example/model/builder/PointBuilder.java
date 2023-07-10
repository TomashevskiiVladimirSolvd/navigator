package org.example.model.builder;

import org.example.model.Point;

public class PointBuilder {
    private Integer id;
    private double xCoordinate;
    private double yCoordinate;

    public PointBuilder() {

    }
    public PointBuilder setId(Integer id){
        this.id=id;
        return this;
    }
    public PointBuilder setXCoordinate(double xCoordinate) {
        this.xCoordinate = xCoordinate;
        return this;
    }
    public PointBuilder setYCoordinate(double yCoordinate) {
        this.yCoordinate = yCoordinate;
        return this;
    }
    public Point getPoint() {
        return new Point(xCoordinate,yCoordinate);
    }


}