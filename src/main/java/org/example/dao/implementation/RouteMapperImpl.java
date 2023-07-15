package org.example.dao.implementation;

import org.apache.ibatis.session.SqlSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.configuration.MyBatisSession;
import org.example.dao.interfaces.RouteDAO;
import org.example.model.Point;
import org.example.model.Route;

import java.util.List;


public class RouteMapperImpl implements RouteDAO {
    private static final Logger logger = LogManager.getLogger("RouteMapperImpl");
    private SqlSession sqlSession;

    public RouteMapperImpl() {
    }

    @Override
    public void insertRoute(Route route) {
        sqlSession = MyBatisSession.getSqlSession();
        try {
            sqlSession.insert("insertRoute", route);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
        logger.debug("A route has been created using INSERT INTO routes (start_point, end_point, distance) VALUES (#{startPoint.id}, #{endPoint.id}, #{distance})");
    }

    @Override
    public void updateRoute(Route route) {
        sqlSession = MyBatisSession.getSqlSession();
        try {
            sqlSession.update("updateRoute", route);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
        logger.debug("A route has been updated using UPDATE routes SET distance = #{distance} WHERE id = #{id}");
    }

    @Override
    public Route getRoute(int id) {
        sqlSession = MyBatisSession.getSqlSession();
        Route route;
        try {
            route = sqlSession.selectOne("getRoute", id);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }

        List<Point> wayPoints = getWayPoints(id);
        route.setWayPoints(wayPoints);

        logger.debug("A route has been retrieved using SELECT r.id, r.start_point, r.end_point, r.distance FROM routes r WHERE r.id = #{id}");
        return route;
    }

    @Override
    public List<Route> getRoutes() {
        sqlSession = MyBatisSession.getSqlSession();
        List<Route> routes;
        try {
            routes = sqlSession.selectList("getRoutes");
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }

        for (Route route : routes) {
            List<Point> wayPoints = getWayPoints(route.getId());
            route.setWayPoints(wayPoints);
        }

        logger.debug("A list of routes has retrieved using SELECT r.id, r.start_point, r.end_point, r.distance FROM routes r");
        return routes;
    }

    @Override
    public List<Point> getWayPoints(int id) {
        sqlSession = MyBatisSession.getSqlSession();
        List<Point> wayPoints;
        try {
            RouteDAO routeDAO = sqlSession.getMapper(RouteDAO.class);
            wayPoints = routeDAO.getWayPoints(id);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
        return wayPoints;
    }

    @Override
    public void setWayPoints(Route route, Point wayPoint, int order) {
        sqlSession = MyBatisSession.getSqlSession();
        try {
            RouteDAO routeDAO = sqlSession.getMapper(RouteDAO.class);
            routeDAO.setWayPoints(route, wayPoint, order);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
        logger.debug("Waypoints have been set using INSERT INTO waypoints (points_id, routes_id) VALUES (#{point.id}, #{route.id})");
    }

    @Override
    public void setEndPoint(Point endPoint, Route route) {
        sqlSession = MyBatisSession.getSqlSession();
        try {
            RouteDAO routeDAO = sqlSession.getMapper(RouteDAO.class);
            routeDAO.setEndPoint(endPoint, route);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
        logger.debug("An endpoint has been set using UPDATE routes SET end_point = #{endPoint.id} WHERE id = #{route.id}");
    }

    @Override
    public void setStartPoint(Point startPoint, Route route) {
        sqlSession = MyBatisSession.getSqlSession();
        try {
            RouteDAO routeDAO = sqlSession.getMapper(RouteDAO.class);
            routeDAO.setStartPoint(startPoint, route);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
        logger.debug("An start point has been set using UPDATE routes SET start_point = #{startPoint.id} WHERE id = #{route.id}");
    }

}
