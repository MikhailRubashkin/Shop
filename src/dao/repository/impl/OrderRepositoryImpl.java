package dao.repository.impl;

import dao.connection.ConnectionManager;
import model.Order;
import dao.repository.OrderRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {

    private static final String SAVE_QUERY = "INSERT INTO `order` (user_id) VALUES (?)";
    private static final String UPDATE_QUERY = "UPDATE `order` SET user_id=? WHERE id=?";
    private static final String GET_QUERY = "SELECT * FROM `order` WHERE id=?";
    private static final String GET_ALL_QUERY = "SELECT * FROM `order`";
    private static final String GET_BY_USER_ID_QUERY = "SELECT * FROM `order` WHERE user_id=?";
    private static final String REMOVE_QUERY = "DELETE FROM `order` WHERE id=?";

    private Connection connection;
    private static volatile OrderRepositoryImpl INSTANCE = null;

    private OrderRepositoryImpl() {

    }
    public static OrderRepositoryImpl getInstance() {
        synchronized (BaseRepository.class) {
            if (INSTANCE == null) {
                INSTANCE = new OrderRepositoryImpl();
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

    public Order get(Integer id) throws SQLException {
        final PreparedStatement statement = getConnection().prepareStatement(GET_QUERY);
        statement.setInt(1, id);
        statement.executeQuery();
        ResultSet resultSet = statement.getResultSet();
        if (resultSet.next()) {
            return formOrder(resultSet);
        }
        close(resultSet);
        return null;
    }


    @Override
    public List<Order> getAll() throws SQLException {
        List<Order> result = new ArrayList<>();
        final PreparedStatement statement = getConnection().prepareStatement(GET_ALL_QUERY);
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        while (resultSet.next()) {
            result.add(formOrder(resultSet));
        }
        close(resultSet);
        return result;
    }

    @Override
    public Order save(Order order) throws SQLException {
        final PreparedStatement statement = getConnection().prepareStatement(SAVE_QUERY, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, order.getUserId());
        statement.executeUpdate();
        ResultSet resultSet = statement.getGeneratedKeys();
        if (resultSet.next()) {
            order.setId((int) resultSet.getLong(1));
        }
        close(resultSet);
        return order;
    }

    @Override
    public void update(Order order) throws SQLException {
        final PreparedStatement statement = getConnection().prepareStatement(UPDATE_QUERY);
        statement.setInt(1, order.getUserId());
        statement.setInt(2, order.getId());
        statement.executeUpdate();
    }


    @Override
    public int remove(int id) throws SQLException {
        final PreparedStatement statement = getConnection().prepareStatement(REMOVE_QUERY);
        statement.setLong(1, (long) id);
        return statement.executeUpdate();
    }

    private static void close(ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
    }

    private Order formOrder(ResultSet resultSet) throws SQLException {
        Order order = new Order(resultSet.getInt(1));
        order.setId(resultSet.getInt(1));
        return order;
    }

    @Override
    public List<Order> getByUserId(Integer user_id) throws SQLException {
        List<Order> result = new ArrayList<>();
        final PreparedStatement statement = getConnection().prepareStatement(GET_BY_USER_ID_QUERY);
        statement.setInt(1, user_id);
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        while (resultSet.next()) {
            result.add(formOrder(resultSet));
        }
        close(resultSet);
        return result;
    }
}
