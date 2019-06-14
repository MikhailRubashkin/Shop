package service;

import model.User;

import java.util.List;

public interface UserService {

    User getUser(int id);

    List<User> getUsers();

    User addUser(User user);

    void deleteUser(int id);

    void updateUser(User user);

    User getUserByName(String login);

    User addUserByName(String login);
}
