package org.example.service.implementation;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.dao.implementation.UserMapperImpl;
import org.example.dao.interfaces.UserDAO;
import org.example.model.User;
import org.example.service.interfaces.IUserService;



public class UserService implements IUserService {
    private static final Logger logger = LogManager.getLogger("UserService");
    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserMapperImpl();
    }

    @Override
    public User create(User user) {
        user.setId(null);
        userDAO.insertUser(user);
        logger.debug("A new user has been created.");
        return user;
    }

    @Override
    public void update(User user) {
        userDAO.updateUser(user);
        logger.debug("A user has been updated.");
    }

    @Override
    public User getUser(int id) {
        logger.debug("A user has been retrieved by id.");
        return userDAO.getUser(id);
    }

    @Override
    public User getUser(String email) {
        logger.debug("A user has been retrieved by email.");
        return userDAO.getUser(email);
    }

    @Override
    public List<User> getUsers() {
        logger.debug("A list of users has been retrieved.");
        return userDAO.getUsers();
    }
}
