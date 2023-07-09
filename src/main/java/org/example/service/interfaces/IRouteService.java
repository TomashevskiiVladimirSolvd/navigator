package org.example.service.interfaces;

import org.example.model.Route;

import java.util.List;

public interface IRouteService {
    public Route create(Route route);
    public void update(Route route);
    Route getRoute(int id);
    List<Route> getRoutes();
}
