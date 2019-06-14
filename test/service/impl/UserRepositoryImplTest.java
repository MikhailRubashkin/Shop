package service.impl;


import model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.UserService;

import java.util.List;

public class UserRepositoryImplTest {

    private UserService userService;

    @Before
    public void setUp() {
        userService = UserServiceImpl.getInstance();
    }

    @Test
    public void getInstanse() {
        Assert.assertNotNull(userService);
    }

    @Test
    public void getUser() {
        User user = new User();
        user.setName("get");
        user.setPassword("100");
        final User newUser = userService.addUser(user);
        Assert.assertNotNull(newUser);
        final User foundUser = userService.getUser(newUser.getId());
        Assert.assertNotNull(foundUser);
        Assert.assertEquals(foundUser, newUser);
        userService.deleteUser(newUser.getId());
    }

    @Test
    public void getUsers() {
        User user = new User();
        user.setName("getAll");
        user.setPassword("100");
        final User newUser = userService.addUser(user);
        Assert.assertNotNull(newUser);
        final List<User> users = userService.getUsers();
        Assert.assertTrue(users.size() > 0);
        userService.deleteUser(newUser.getId());
    }

    @Test
    public void addUser() {
        User user = new User();
        user.setName("add");
        user.setPassword("100");
        final User newUser = userService.addUser(user);
        Assert.assertNotNull(newUser);
        Assert.assertEquals(user, newUser);
        userService.deleteUser(newUser.getId());
    }

    @Test
    public void deleteUser() {
        User user = new User();
        user.setName("get");
        user.setPassword("100");
        final User newUser = userService.addUser(user);
        Assert.assertNotNull(newUser);
        userService.deleteUser(newUser.getId());
        final User foundUser = userService.getUser(newUser.getId());
        Assert.assertNull(foundUser);
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setName("update");
        user.setPassword("100");
        final User newUser = userService.addUser(user);
        Assert.assertNotNull(newUser);
        user.setId(newUser.getId());
        user.setName("updateTest");
        user.setPassword("100");
        userService.updateUser(user);
        final User foundUser = userService.getUser(newUser.getId());
        Assert.assertNotNull(foundUser);
        Assert.assertEquals(foundUser, user);
        userService.deleteUser(user.getId());
    }
}

