package org.example.dao.MapperImpl;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.example.dao.ShortestPathDAO;
import org.example.dao.myBatis.MyBatisSessionManager;
import org.example.model.Route;
import org.example.model.ShortestPath;

public class ShortestPathMapperImpl implements ShortestPathDAO {
    private final Logger logger = Logger.getLogger("GLOBAL");
    private SqlSession sqlSession;

    public ShortestPathMapperImpl() {
    }

    @Override
    public void insertShortestPath(ShortestPath shortestPath) {
        sqlSession = MyBatisSessionManager.getSqlSession();
        try {
            sqlSession.insert("insertShortestPath", shortestPath);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void updateShortestPath(ShortestPath shortestPath) {
        sqlSession = MyBatisSessionManager.getSqlSession();
        try {
            sqlSession.update("updateShortestPath", shortestPath);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public ShortestPath getShortestPath(int id) {
        sqlSession = MyBatisSessionManager.getSqlSession();
        ShortestPath shortestPath;
        try {
            shortestPath = sqlSession.selectOne("getShortestPath", id);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
        return shortestPath;
    }
}
