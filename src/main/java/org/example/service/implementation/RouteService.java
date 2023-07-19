package org.example.service.implementation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.builder.RouteBuilder;
import org.example.dao.implementation.RouteMapperImpl;
import org.example.dao.interfaces.RouteDAO;
import org.example.model.Point;
import org.example.model.Route;
import org.example.service.interfaces.IPointService;
import org.example.service.interfaces.IRouteService;

import java.util.List;
import java.util.stream.Collectors;

public class RouteService implements IRouteService {
    private static final Logger logger = LogManager.getLogger("RouteService");
    private final RouteDAO routeDAO;
    private final IPointService pointService;


    public RouteService() {
        this.routeDAO = new RouteMapperImpl();
        this.pointService = new PointService();
    }

    public Route create(Route route) {
        route.setId(null);
        routeDAO.insertRoute(route);

        if (route.getStartPoint() != null) {
            routeDAO.setStartPoint(route.getStartPoint(), route);
        }

        if (route.getEndPoint() != null) {
            routeDAO.setEndPoint(route.getEndPoint(), route);
        }

        if (route.getWayPoints() != null) {
            List<Point> wayPoints = route.getWayPoints();
            int order = 0;
            for (Point wayPoint : wayPoints) {
                routeDAO.setWayPoints(route, wayPoint, ++order);
            }
        }
        logger.debug("A new route has been created.");
        return route;
    }

    public void update(Route route) {
        routeDAO.updateRoute(route);
        logger.debug("A route has been updated.");
    }

    public Route getRoute(int id) {
        logger.debug("A route has been retrieved.");
        return routeDAO.getRoute(id);
    }

    public List<Route> getRoutes() {
        logger.debug("A list of routes has been retrieved.");
        return routeDAO.getRoutes();
    }


    public List<Point> getWayPoints(int id) {
        return routeDAO.getWayPoints(id);
    }

}
