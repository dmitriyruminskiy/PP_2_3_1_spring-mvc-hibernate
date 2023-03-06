package web.service;

import web.entities.User;

import java.util.List;

public interface UsersService {
    public List<User> getAllUsers();
    public User show (int id);
    public void save(User user);
    public void update(User updateUser);
    public void delete(int id);
}
