package org.example.dao.interfaces;

import org.example.model.Point;

import java.util.List;

public interface PointDAO {
    void insertPoint(Point point);
    void deletePoint(int id);
    Point getPoint(int id);
    List<Point> getPoints();
}
