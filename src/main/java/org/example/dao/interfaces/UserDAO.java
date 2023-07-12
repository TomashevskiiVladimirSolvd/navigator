package org.example.dao.interfaces;

import org.example.model.User;

import java.util.List;

public interface UserDAO {
    public void insertUser(User user);
    public void updateUser(User user);
    public User getUser(int id);
    public List<User> getUsers();
}
