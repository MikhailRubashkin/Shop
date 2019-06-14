package dao.repository.impl;

import dao.connection.ConnectionManager;
import model.Bunch;
import dao.repository.BunchRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BunchRepositoryImpl implements BunchRepository {

    private static final String SAVE_QUERY = "INSERT INTO Bunch (scooter_id , order_id, quantity) VALUES (?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE Bunch SET scooter_id =?, order_id=?, quantity=? WHERE id=?";
    private static final String GET_QUERY = "SELECT * FROM Bunch WHERE id=?";
    private static final String GET_ALL_QUERY = "SELECT * FROM Bunch";
    private static final String REMOVE_QUERY = "DELETE FROM Bunch WHERE id=?";
    private static final String GET_BUNCHS_BY_ORDER_ID = "SELECT * FROM Bunch WHERE ORDER_ID = ?";

    private Connection connection;
    private static volatile BunchRepositoryImpl INSTANCE = null;

    private BunchRepositoryImpl() {

    }

    public static BunchRepositoryImpl getInstance() {
        synchronized (BaseRepository.class) {
            if (INSTANCE == null) {
                INSTANCE = new BunchRepositoryImpl();
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

    public Bunch get(Integer id) throws SQLException {
        final PreparedStatement statement = getConnection().prepareStatement(GET_QUERY);
        statement.setLong(1, (long) id);
        statement.executeQuery();
        ResultSet resultSet = statement.getResultSet();
        if (resultSet.next()) {
            return formBunch(resultSet);
        }
        close(resultSet);
        return null;
    }

    public List<Bunch> getByOrderId(Integer order_id) throws SQLException {
        final PreparedStatement statement = getConnection().prepareStatement(GET_BUNCHS_BY_ORDER_ID);
        statement.setInt(1, order_id);
        statement.execute();
        List<Bunch> list = new ArrayList<>();
        ResultSet rs = statement.getResultSet();
        while (rs.next()) {
            list.add(populateEntity(rs));
        }
        close(rs);
        return list;
    }
    @Override
    public List<Bunch> getAll() throws SQLException {
        List<Bunch> result = new ArrayList<>();
        final PreparedStatement statement = getConnection().prepareStatement(GET_ALL_QUERY);
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        while (resultSet.next()) {
            result.add(formBunch(resultSet));
        }
        close(resultSet);
        return result;
    }

    @Override
    public Bunch save(Bunch bunch) throws SQLException {
        final PreparedStatement statement = getConnection().prepareStatement(SAVE_QUERY, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, bunch.getScooter_id());
        statement.setInt(2, bunch.getOrder_id());
        statement.setInt(3, bunch.getQuantity());
        statement.executeUpdate();
        ResultSet resultSet = statement.getGeneratedKeys();
        if (resultSet.next()) {
            bunch.setId((int) resultSet.getInt(1));
        }
        close(resultSet);
        return bunch;
    }

    @Override
    public void update(Bunch bunch) throws SQLException {
        final PreparedStatement statement = getConnection().prepareStatement(UPDATE_QUERY);
        statement.setInt(4, bunch.getId());
        statement.setInt(1, bunch.getScooter_id());
        statement.setInt(2, bunch.getOrder_id());
        statement.setInt(3, bunch.getQuantity());
        statement.executeUpdate();
    }


  /*  @Override
    public void update(Bunch bunch) throws SQLException {
        final PreparedStatement statement = getConnection().prepareStatement(UPDATE_QUERY);
        statement.setInt(4, bunch.getId());
        statement.setInt(1, bunch.getScooterId());
        statement.setInt(2, bunch.getId());
        statement.setInt(3, bunch.getQuantity());
        statement.executeUpdate();
    }*/

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

    private Bunch formBunch(ResultSet resultSet) throws SQLException {
        Bunch bunch = new Bunch();
        bunch.setId(resultSet.getInt(1));
        bunch.setScooter_id(resultSet.getInt(2));
        bunch.setOrder_id(resultSet.getInt(3));
        bunch.setQuantity(resultSet.getInt(4));
        return bunch;
    }



    private Bunch populateEntity(ResultSet rs) throws SQLException {
        Bunch entity = new Bunch();
        entity.setId(rs.getInt(1));
        entity.setScooter_id(rs.getInt(2));
        entity.setOrder_id(rs.getInt(3));
        entity.setQuantity(rs.getInt(4));
        return entity;
    }
}
    
    

