package org.example.dao.implementation;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.example.configuration.MyBatisSession;
import org.example.dao.interfaces.PointDAO;
import org.example.model.Point;

import java.util.List;

public class PointMapperImpl implements PointDAO {
    private final Logger logger = Logger.getLogger("GLOBAL");

    public PointMapperImpl() {

    }

    @Override
    public void insertPoint(Point point) {
        try (SqlSession sqlSession = MyBatisSession.getSqlSession();) {
            sqlSession.insert("insertPoint", point);
            sqlSession.commit();
        }
    }

    @Override
    public void deletePoint(int id) {
        try (SqlSession sqlSession = MyBatisSession.getSqlSession();) {
            sqlSession.delete("deletePoint", id);
            sqlSession.commit();
        }
    }

    @Override
    public Point getPoint(int id) {
        Point point;
        try (SqlSession sqlSession = MyBatisSession.getSqlSession();) {
            point = sqlSession.selectOne("getPoint", id);
            sqlSession.commit();
        }
        return point;
    }

    @Override
    public List<Point> getPoints() {
        List<Point> points;
        try (SqlSession sqlSession = MyBatisSession.getSqlSession();) {
            points = sqlSession.selectList("getPoints");
            sqlSession.commit();
        }
        return points;
    }
}