package org.example.dao.MapperImpl;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.example.dao.UserDAO;
import org.example.dao.myBatis.MyBatisSessionManager;
import org.example.model.User;

import java.util.List;

public class UserMapperImpl implements UserDAO {
    private final Logger logger = Logger.getLogger("GLOBAL");
    private SqlSession sqlSession;

    public UserMapperImpl() {
    }

    @Override
    public void insertUser(User user) {
        sqlSession = MyBatisSessionManager.getSqlSession();
        try {
            sqlSession.insert("insertUser", user);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void updateUser(User user) {
        sqlSession = MyBatisSessionManager.getSqlSession();
        try {
            sqlSession.update("updateUser", user);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public User getUser(int id) {
        sqlSession = MyBatisSessionManager.getSqlSession();
        User user;
        try {
            user = sqlSession.selectOne("getUser", id);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
        return user;
    }

    @Override
    public List<User> getUsers() {
        sqlSession = MyBatisSessionManager.getSqlSession();
        List<User> users;
        try {
            users = sqlSession.selectList("getUsers");
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
        return users;
    }
}
