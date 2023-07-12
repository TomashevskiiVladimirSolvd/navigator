package org.example.dao.interfaces;

import org.example.model.Point;

import java.util.List;

public interface PointDAO {
    public void insertPoint(Point point);
    public void deletePoint(int id);
    public Point getPoint(int id);
    public List<Point> getPoints();
}
