package org.example.service.interfaces;


import java.util.List;
import org.example.model.User;


public interface IUserService {
    public User create(User user);
    public void update(User user);
    public User getUser(int id);
    public User getUser(String email);
    public List<User> getUsers();
}
