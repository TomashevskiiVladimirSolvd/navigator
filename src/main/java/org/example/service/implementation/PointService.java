package org.example.service.implementation;

import org.example.dao.PointDAO;
import org.example.dao.PointDAOImpl;
import org.example.model.Point;
import org.example.service.interfaces.IPointService;

import java.util.List;

public class PointService implements IPointService {
    private final PointDAO pointDAO;

    public PointService() {
        this.pointDAO = new PointDAOImpl();
    }

    public Point create(Point point) {
        point.setId(null);
        pointDAO.insertPoint(point);
        return point;
    }

    public void delete(Point point) {
        pointDAO.deletePoint(point.getId());
    }

    public Point getPoint(int id) {
        return pointDAO.getPoint(id);
    }

    public List<Point> getPoints() {
        return pointDAO.getPoints();
    }
}
