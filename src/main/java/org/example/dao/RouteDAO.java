package org.example.dao;

import org.example.model.Route;

import java.util.List;

public interface RouteDAO {
    void insertRoute(Route route);
    Route getRoute(int id);
    List<Route> getRoutes();
}
