package org.example.service.implementation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.dao.implementation.PointMapperImpl;
import org.example.dao.interfaces.PointDAO;
import org.example.model.Point;
import org.example.service.interfaces.IPointService;

import java.util.List;

public class PointService implements IPointService {
    private static final Logger logger = LogManager.getLogger("PointService");
    private final PointDAO pointDAO;

    public PointService() {
        this.pointDAO = new PointMapperImpl();
    }
    public Point create(Point point) {
        point.setId(null);
        pointDAO.insertPoint(point);
        logger.debug("A new point has been created.");
        return point;
    }
    public void delete(Point point) {
        pointDAO.deletePoint(point.getId());
        logger.debug("A point has been deleted.");
    }

    public Point getPoint(int id) {
        logger.debug("A point has been retrieved.");
        return pointDAO.getPoint(id);
    }

    public List<Point> getPoints() {
        logger.debug("A list of points has been retrieved.");
        return pointDAO.getPoints();
    }
}
