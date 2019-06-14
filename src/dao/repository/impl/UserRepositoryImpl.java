package dao.repository.impl;

import com.mysql.jdbc.Statement;
import dao.connection.ConnectionManager;
import model.User;
import dao.repository.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private static final String GET_QUERY = "SELECT * FROM user WHERE id=?";
    private static final String GET_ALL_QUERY = "SELECT * FROM user ";
    private static final String SAVE_QUERY = "INSERT INTO user (name, password) VALUES (?, ?)";
    private static final String UPDATE_QUERY = "UPDATE user SET name =?, password=? WHERE id=?";
    private static final String REMOVE_QUERY = "DELETE FROM user WHERE id=?";
    private static final String GET_USER_BY_NAME = "SELECT * FROM user WHERE name=?";
    private static final String ADD_USER_BY_NAME = "INSERT INTO user (name) VALUES (?)";

    private Connection connection;
    private static volatile UserRepositoryImpl INSTANCE = null;

    private UserRepositoryImpl() {
    }

    public static UserRepositoryImpl getInstance() {
        synchronized (BaseRepository.class) {
            if (INSTANCE == null) {
                INSTANCE = new UserRepositoryImpl();
            }
        }
        return INSTANCE;
    }

    private Connection getConnection() {
        if (connection == null) {
            connection = ConnectionManager.getConnection();
        }
        return connection;
    }

    public User get(Integer id) throws SQLException {
        final PreparedStatement statement = getConnection().prepareStatement(GET_QUERY);
        statement.setLong(1, (long) id);
        statement.executeQuery();
        ResultSet resultSet = statement.getResultSet();
        if (resultSet.next()) {
            return formUser(resultSet);
        }
        closeResultSet(resultSet);
        return null;
    }

    @Override
    public List<User> getAll() throws SQLException {
        List<User> result = new ArrayList<>();
        final PreparedStatement statement = getConnection().prepareStatement(GET_ALL_QUERY);
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        while (resultSet.next()) {
            result.add(formUser(resultSet));
        }
        closeResultSet(resultSet);
        return result;
    }

    @Override
    public User save(User user) throws SQLException {
        final PreparedStatement statement = getConnection().prepareStatement(SAVE_QUERY, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, user.getName());
        statement.setString(2, user.getPassword());
        statement.executeUpdate();
        ResultSet resultSet = statement.getGeneratedKeys();
        if (resultSet.next()) {
            user.setId((int) resultSet.getLong(1));
        }
        closeResultSet(resultSet);
        return user;
    }

    @Override
    public void update(User user) throws SQLException {
        final PreparedStatement statement = getConnection().prepareStatement(UPDATE_QUERY);
        statement.setString(1, user.getName());
        statement.setString(2, user.getPassword());
        statement.setInt(3, user.getId());
        statement.executeUpdate();
    }

    @Override
    public int remove(int id) throws SQLException {
        final PreparedStatement statement = getConnection().prepareStatement(REMOVE_QUERY);
        statement.setLong(1, (long) id);
        return statement.executeUpdate();
    }

    @Override
    public User getByName(String name) throws SQLException {
        final PreparedStatement psGetByLogin = getConnection().prepareStatement(GET_USER_BY_NAME);
        psGetByLogin.setString(1, name);
        ResultSet resultSet = psGetByLogin.executeQuery();
        if (resultSet.next()) {
            return formUser(resultSet);
        }
        closeResultSet(resultSet);
        return null;
    }

    @Override
    public User addByName(String name) throws SQLException {
        final PreparedStatement psAddByLogin = getConnection().prepareStatement(ADD_USER_BY_NAME);
        psAddByLogin.setString(1, name);
        ResultSet resultSet = psAddByLogin.executeQuery();
        if (resultSet.next()) {
            return formUser(resultSet);
        }
        closeResultSet(resultSet);
        return null;
    }

    private static void closeResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
    }

    private User formUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(1));
        user.setName(resultSet.getString(2));
        user.setPassword(resultSet.getString(3));
        return user;
    }
}



