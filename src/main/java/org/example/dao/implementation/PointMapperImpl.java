package org.example.dao.MapperImpl;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.example.dao.PointDAO;
import org.example.dao.myBatis.MyBatisSessionManager;
import org.example.model.Point;

import java.util.List;

public class PointMapperImpl implements PointDAO {
    private final Logger logger = Logger.getLogger("GLOBAL");
    private SqlSession sqlSession;

    public PointMapperImpl() {
    }

    @Override
    public void insertPoint(Point point) {
        sqlSession = MyBatisSessionManager.getSqlSession();
        try {
            sqlSession.insert("insertPoint", point);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void deletePoint(int id) {
        sqlSession = MyBatisSessionManager.getSqlSession();
        try {
            sqlSession.delete("deletePoint", id);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public Point getPoint(int id) {
        sqlSession = MyBatisSessionManager.getSqlSession();
        Point point;
        try {
            point = sqlSession.selectOne("getPoint", id);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
        return point;
    }

    @Override
    public List<Point> getPoints() {
        sqlSession = MyBatisSessionManager.getSqlSession();
        List<Point> points;
        try {
            points = sqlSession.selectList("getPoints");
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
        return points;
    }
}
