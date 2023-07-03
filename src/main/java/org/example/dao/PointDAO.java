package org.example.dao;

import org.example.model.Point;

import java.util.List;

public interface PointDAO {
    void insertPoint(Point point);
    void deletePoint(Point point);
    Point getPoint(int id);
    List<Point> getPoints();
}
