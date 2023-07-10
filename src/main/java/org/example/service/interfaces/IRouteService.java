package org.example.service.interfaces;

import org.example.builder.RouteBuilder;
import org.example.model.Route;

import java.util.List;

public interface IRouteService {
    public Route create(RouteBuilder routeBuilder);
    public void update(Route route);
    Route getRoute(int id);
    List<Route> getRoutes();
}
