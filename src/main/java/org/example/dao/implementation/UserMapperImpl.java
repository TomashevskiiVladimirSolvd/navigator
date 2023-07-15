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
        logger.debug("A user has been created using INSERT INTO users (name, surname, email, password) VALUES (#{name}, #{surname}, #{email}, #{password});");
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
        logger.debug("A user has been updated using UPDATE users SET name = #{name}, surname = #{surname}, email = #{email}, password = #{password} WHERE id = #{id};");
    }

    @Override
    public User getUser(int id) {
        sqlSession = MyBatisSession.getSqlSession();
        User user;
        try {
            user = sqlSession.selectOne("getUserById", id);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
        logger.debug("A user has been retrieved by id using SELECT id, name, surname, email, password FROM users WHERE id = #{id};");
        return user;
    }

    @Override
    public User getUser(String email) {
        sqlSession = MyBatisSession.getSqlSession();
        User user;
        try {
            user = sqlSession.selectOne("getUserByEmail", email);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
        logger.debug("A user has been retrieved by email using SELECT id, name, surname, email, password FROM users WHERE email = #{email};");
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
        logger.debug("A list of users has been retrieved using SELECT id, name, surname, email, password FROM users;");
        return users;
    }

}
