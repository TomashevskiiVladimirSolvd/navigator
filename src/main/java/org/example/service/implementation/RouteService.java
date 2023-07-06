package org.example.service.implementation;

import org.example.dao.RouteDAO;
import org.example.dao.RouteDAOImpl;
import org.example.model.Point;
import org.example.model.Route;
import org.example.service.interfaces.IPointService;
import org.example.service.interfaces.IRouteService;

import java.util.List;

public class RouteService implements IRouteService {
    private final RouteDAO routeDAO;
    private final IPointService pointService;


    public RouteService() {
        this.routeDAO = new RouteDAOImpl();
        this.pointService = new PointService();
    }

    public Route create(Route route) {
        route.setId(null);
        routeDAO.insertRoute(route);

        if (route.getStartPoint() != null) {
            Point startPoint = pointService.create(route.getStartPoint());
            route.setStartPoint(startPoint);
        }

        if (route.getEndPoint() != null) {
            Point endPoint = pointService.create(route.getEndPoint());
            route.setStartPoint(endPoint);
        }
        return route;
    }

    public void update(Route route) {
        routeDAO.updateRoute(route);
    }

    public Route getRoute(int id) {
        return routeDAO.getRoute(id);
    }

    public List<Route> getRoutes() {
        return routeDAO.getRoutes();
    }

}
