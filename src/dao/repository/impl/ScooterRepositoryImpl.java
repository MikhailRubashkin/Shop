package dao.repository.impl;

import dao.connection.ConnectionManager;
import model.Scooter;
import dao.repository.ScooterRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScooterRepositoryImpl implements ScooterRepository {

    private static final String SAVE_QUERY = "INSERT INTO SCOOTER (MODEL, PRICE) VALUES (?, ?)";
    private static final String UPDATE_QUERY = "UPDATE SCOOTER SET MODEL=?, PRICE=? WHERE id=?";
    private static final String GET_QUERY = "SELECT * FROM SCOOTER WHERE id=?";
    private static final String GET_ALL_QUERY = "SELECT * FROM SCOOTER";
    private static final String REMOVE_QUERY = "DELETE FROM SCOOTER WHERE id=?";

    private Connection connection;
    private static volatile ScooterRepositoryImpl INSTANCE = null;

    private ScooterRepositoryImpl() {

    }

    public static ScooterRepositoryImpl getInstance() {
        synchronized (BaseRepository.class) {
            if (INSTANCE == null) {
                INSTANCE = new ScooterRepositoryImpl();
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

    public Scooter get(Integer id) throws SQLException {
        final PreparedStatement statement = getConnection().prepareStatement(GET_QUERY);
        statement.setLong(1, (long) id);
        statement.executeQuery();
        ResultSet resultSet = statement.getResultSet();
        if (resultSet.next()) {
            return formScooter(resultSet);
        }
        close(resultSet);
        return null;
    }

    @Override
    public List<Scooter> getAll() throws SQLException {
        List<Scooter> result = new ArrayList<>();
        final PreparedStatement statement = getConnection().prepareStatement(GET_ALL_QUERY);
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        while (resultSet.next()) {
            result.add(formScooter(resultSet));
        }
        close(resultSet);
        return result;
    }

    @Override
    public Scooter save(Scooter scooter) throws SQLException {
        final PreparedStatement statement = getConnection().prepareStatement(SAVE_QUERY, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, scooter.getModel());
        statement.setDouble(2, scooter.getPrice());
        statement.executeUpdate();
        ResultSet resultSet = statement.getGeneratedKeys();
        if (resultSet.next()) {
            scooter.setId((int) resultSet.getLong(1));
        }
        close(resultSet);
        return scooter;
    }

    @Override
    public void update(Scooter scooter) throws SQLException {
        final PreparedStatement statement = getConnection().prepareStatement(UPDATE_QUERY);
        statement.setInt(3, scooter.getId());
        statement.setString(1, scooter.getModel());
        statement.setDouble(2, scooter.getPrice());
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

    private Scooter formScooter(ResultSet resultSet) throws SQLException {
        Scooter scooter = new Scooter();
        scooter.setId(resultSet.getInt(1));
        scooter.setModel(resultSet.getString(2));
        scooter.setPrice(resultSet.getDouble(3));
        return scooter;
    }

}