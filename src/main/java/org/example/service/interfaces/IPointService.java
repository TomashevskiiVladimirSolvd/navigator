package org.example.service.interfaces;

import org.example.model.Point;

import java.util.List;

public interface IPointService {
    Point create(Point point);
    void delete(Point point);
    Point getPoint(int id);
    List<Point> getPoints();
}
