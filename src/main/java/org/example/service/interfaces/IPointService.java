package org.example.service.interfaces;

import org.example.model.Point;

import java.util.List;

public interface IPointService {
    public Point create(Point point);
    public void delete(Point point);
    public Point getPoint(int id);
    public List<Point> getPoints();
}
