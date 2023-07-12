package org.example.dao.implementation;

import org.apache.ibatis.annotations.Param;
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
        return routes;
    }

    @Override
    public void setWayPoints(Route route, Point wayPoint) {
        sqlSession = MyBatisSession.getSqlSession();
        try {
            RouteDAO routeDAO = sqlSession.getMapper(RouteDAO.class);
            routeDAO.setWayPoints(route, wayPoint);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
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
    }
}
