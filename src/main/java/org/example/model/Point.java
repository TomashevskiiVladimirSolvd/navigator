package org.example.model;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.builder.PointBuilder;

import java.util.Objects;

public class Point {
  private static final Logger logger = LogManager.getLogger("Point");

  private Integer id;
  private double xCoordinate;
  private double yCoordinate;
  private String cityName;



  public Point() {
  }

  public Point(Integer id) {
    this.id = id;
  }

  public Point(double xCoordinate, double yCoordinate, String cityName) {
    this.xCoordinate = xCoordinate;
    this.yCoordinate = yCoordinate;
    this.cityName = cityName;
  }


  public Integer getId() {
    logger.debug("The point id has been retrieved.");
    return id;
  }

  public void setId(Integer id) {
    logger.debug("The point id has been set.");
    this.id = id;
  }

  public double getXCoordinate() {
    logger.debug("The X coordinate has been retrieved.");
    return xCoordinate;
  }

  public void setXCoordinate(double xCoordinate) {
    logger.debug("The X coordinate has been set.");
    this.xCoordinate = xCoordinate;
  }

  public double getYCoordinate() {
    logger.debug("The Y coordinate has been retrieved.");
    return yCoordinate;
  }

  public void setYCoordinate(double yCoordinate) {
    logger.debug("The Y coordinate has been set.");
    this.yCoordinate = yCoordinate;
  }

  public String getCityName() {
    logger.debug("The city name has been retrieved.");
    return cityName;
  }

  public void setCityName(String cityName) {
    logger.debug("The city name has been set.");
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


