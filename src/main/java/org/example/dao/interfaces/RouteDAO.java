package org.example.dao.interfaces;

import org.example.model.Route;

import java.util.List;

public interface RouteDAO {
    void insertRoute(Route route);
    void updateRoute(Route route);
    Route getRoute(int id);
    List<Route> getRoutes();
}
