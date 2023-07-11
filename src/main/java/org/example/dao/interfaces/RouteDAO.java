package org.example.dao.interfaces;

import org.apache.ibatis.annotations.Param;
import org.example.model.Point;
import org.example.model.Route;

import java.util.List;

public interface RouteDAO {
    public void insertRoute(Route route);
    public void updateRoute(Route route);
    public Route getRoute(int id);
    public List<Route> getRoutes();

    public void setWayPoints(@Param("routes") Route route, @Param("points") Point wayPoint);
}
