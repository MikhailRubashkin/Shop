package service.impl;


import dao.repository.UserRepository;
import dao.repository.impl.UserRepositoryImpl;
import model.User;
import service.UserService;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class UserServiceImpl extends AbstractService implements UserService {

    private static UserServiceImpl instance;

    private UserRepository userRepository = UserRepositoryImpl.getInstance();

    private UserServiceImpl() {
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public User getUser(int id) {
        try {
            return userRepository.get(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getUsers() {
        try {
            return userRepository.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public User addUser(User user) {
        try {
            return userRepository.save(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteUser(int id) {
        try {
            userRepository.remove(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        try {
            userRepository.update(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserByName(String name) {
        try {
            return userRepository.getByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User addUserByName(String name) {
        try {
            return userRepository.addByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}


