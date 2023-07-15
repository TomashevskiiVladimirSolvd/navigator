package org.example.dao.implementation;

import org.apache.ibatis.session.SqlSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.configuration.MyBatisSession;
import org.example.dao.interfaces.PointDAO;
import org.example.model.Point;

import java.util.List;

public class PointMapperImpl implements PointDAO {
    private static final Logger logger = LogManager.getLogger("PointMapperImpl");
    private SqlSession sqlSession;

    public PointMapperImpl() {
    }

    @Override
    public void insertPoint(Point point) {
        sqlSession = MyBatisSession.getSqlSession();
        try {
            sqlSession.insert("insertPoint", point);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
        logger.debug("A point has been created using INSERT INTO points (x_coordinate, y_coordinate, city_name) VALUES (#{xCoordinate}, #{yCoordinate}, #{cityName})");
    }

    @Override
    public void deletePoint(int id) {
        sqlSession = MyBatisSession.getSqlSession();
        try {
            sqlSession.delete("deletePoint", id);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
        logger.debug("A point has been deleted using DELETE FROM points WHERE id = #{id}");
    }

    @Override
    public Point getPoint(int id) {
        sqlSession = MyBatisSession.getSqlSession();
        Point point;
        try {
            point = sqlSession.selectOne("getPoint", id);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
        logger.debug("A point has been retrieved using SELECT id, x_coordinate, y_coordinate, city_name FROM points WHERE id = #{id}");
        return point;
    }

    @Override
    public List<Point> getPoints() {
        sqlSession = MyBatisSession.getSqlSession();
        List<Point> points;
        try {
            points = sqlSession.selectList("getPoints");
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
        logger.debug("A list of points has been retrieved using SELECT id, x_coordinate, y_coordinate, city_name FROM points");
        return points;
    }

}