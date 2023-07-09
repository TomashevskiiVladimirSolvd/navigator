package org.example.dao.interfaces;

import org.example.model.User;

import java.util.List;

public interface UserDAO {
    void insertUser(User user);
    void updateUser(User user);
    User getUser(int id);
    List<User> getUsers();
}
