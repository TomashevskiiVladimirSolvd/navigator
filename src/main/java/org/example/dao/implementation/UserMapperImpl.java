package org.example.dao.implementation;

import org.apache.ibatis.session.SqlSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.configuration.MyBatisSession;
import org.example.dao.interfaces.UserDAO;
import org.example.model.User;

import java.util.List;

public class UserMapperImpl implements UserDAO {
    private static final Logger logger = LogManager.getLogger("UserMapperImpl");
    private SqlSession sqlSession;

    public UserMapperImpl() {
    }

    @Override
    public void insertUser(User user) {
        sqlSession = MyBatisSession.getSqlSession();
        try {
            sqlSession.insert("insertUser", user);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void updateUser(User user) {
        sqlSession = MyBatisSession.getSqlSession();
        try {
            sqlSession.update("updateUser", user);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public User getUser(int id) {
        sqlSession = MyBatisSession.getSqlSession();
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
        sqlSession = MyBatisSession.getSqlSession();
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
