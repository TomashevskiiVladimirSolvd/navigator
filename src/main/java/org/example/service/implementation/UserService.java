package org.example.service.implementation;

import java.util.List;
import org.example.dao.implementation.UserMapperImpl;
import org.example.dao.interfaces.UserDAO;
import org.example.model.User;
import org.example.service.interfaces.IUserService;

public class UserService implements IUserService {
    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserMapperImpl();
    }

    @Override
    public User create(User user) {
        user.setId(null);
        userDAO.insertUser(user);
        return user;
    }

    @Override
    public void update(User user) {
        userDAO.updateUser(user);
    }

    @Override
    public User getUser(int id) {
        return userDAO.getUser(id);
    }

    @Override
    public User getUser(String email) {
        return userDAO.getUser(email);
    }

    @Override
    public List<User> getUsers() {
        return userDAO.getUsers();
    }
}
