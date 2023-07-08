package org.example.dao.MapperImpl;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.example.dao.RouteDAO;
import org.example.dao.myBatis.MyBatisSessionManager;
import org.example.model.Point;
import org.example.model.Route;

import java.util.List;

public class RouteMapperImpl implements RouteDAO {
    private final Logger logger = Logger.getLogger("GLOBAL");
    private SqlSession sqlSession;

    public RouteMapperImpl() {
    }

    @Override
    public void insertRoute(Route route) {
        sqlSession = MyBatisSessionManager.getSqlSession();
        try {
            sqlSession.insert("insertRoute", route);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void updateRoute(Route route) {
        sqlSession = MyBatisSessionManager.getSqlSession();
        try {
            sqlSession.update("updateRoute", route);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public Route getRoute(int id) {
        sqlSession = MyBatisSessionManager.getSqlSession();
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
        sqlSession = MyBatisSessionManager.getSqlSession();
        List<Route> routes;
        try {
            routes = sqlSession.selectList("getRoutes");
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
        return routes;
    }
}