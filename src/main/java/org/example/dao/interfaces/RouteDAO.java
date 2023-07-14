package org.example.dao.interfaces;

import org.apache.ibatis.annotations.Param;
import org.example.model.Point;
import org.example.model.Route;

import java.util.List;

public interface RouteDAO {
    public void insertRoute(Route route);
    public void updateRoute(Route route);
    public Route getRoute(@Param("route.id") int id);
    public List<Route> getRoutes();
    public List<Point> getWayPoints(int id);
    public void setWayPoints(@Param("route") Route route, @Param("point") Point wayPoint, @Param("order") int order);
    public void setEndPoint(@Param("endPoint") Point endPoint, @Param("route") Route route);
    public void setStartPoint(@Param("startPoint") Point startPoint, @Param("route") Route route);
}
