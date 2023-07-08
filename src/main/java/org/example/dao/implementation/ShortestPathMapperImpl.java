package org.example.dao.implementation;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.example.dao.interfaces.ShortestPathDAO;
import org.example.configuration.MyBatisSession;
import org.example.model.ShortestPath;

public class ShortestPathMapperImpl implements ShortestPathDAO {
    private SqlSession sqlSession;

    public ShortestPathMapperImpl() {
    }

    @Override
    public void insertShortestPath(ShortestPath shortestPath) {
        sqlSession = MyBatisSession.getSqlSession();
        try {
            sqlSession.insert("insertShortestPath", shortestPath);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void updateShortestPath(ShortestPath shortestPath) {
        sqlSession = MyBatisSession.getSqlSession();
        try {
            sqlSession.update("updateShortestPath", shortestPath);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public ShortestPath getShortestPath(int id) {
        sqlSession = MyBatisSession.getSqlSession();
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
