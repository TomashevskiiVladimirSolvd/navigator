package org.example.service.implementation;

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
    private final RouteDAO routeDAO;
    private final IPointService pointService;


    public RouteService() {
        this.routeDAO = new RouteMapperImpl();
        this.pointService = new PointService();
    }

    public Route create(RouteBuilder routeBuilder) {
        Route route=routeBuilder.build();
        route.setId(null);
        routeDAO.insertRoute(route);

        if (route.getWayPoints() != null) {
            List<Point> wayPoints = route.getWayPoints().stream()
                    .map(pointService::create)
                    .collect(Collectors.toList());
            for (Point wayPoint : wayPoints) {
                routeDAO.setWayPoints(route, wayPoint);
            }
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
