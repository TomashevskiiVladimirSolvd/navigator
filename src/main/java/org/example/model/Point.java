package org.example.model;

import java.util.Objects;
import org.example.model.builder.PointBuilder;

import java.util.Objects;

public class Point {

  private Integer id;
  private double xCoordinate;
  private double yCoordinate;
  private String cityName;



  public Point() {
  }


  public Point(double xCoordinate, double yCoordinate, String cityName) {
    this.xCoordinate = xCoordinate;
    this.yCoordinate = yCoordinate;
    this.cityName = cityName;
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

  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Point point = (Point) o;
    return Double.compare(point.xCoordinate, xCoordinate) == 0 && Double.compare(point.yCoordinate, yCoordinate) == 0 && Objects.equals(id, point.id) && Objects.equals(cityName, point.cityName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, xCoordinate, yCoordinate, cityName);
  }

  @Override
  public String toString() {
    return "Point{" +
        "id=" + id +
        ", xCoordinate=" + xCoordinate +
        ", yCoordinate=" + yCoordinate +
        ", cityName='" + cityName + '\'' +
        '}';
  }

}


