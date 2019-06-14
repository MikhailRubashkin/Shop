package dao.repository;

import java.sql.SQLException;
import java.util.List;

public interface EntytiRepository<T> {

    T get(Integer id) throws SQLException;

    //List<Bunch> getByOrderId(Integer order_id) throws SQLException;

    List<T> getAll() throws SQLException;

     T save(T t) throws SQLException;

    void update(T t) throws SQLException;

    //void update(api.model.Bunch bunch) throws SQLException;

    int remove(int id) throws SQLException;

    //List<> getByOrderId(long orderId) throws SQLException;
}
