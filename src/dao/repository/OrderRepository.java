package dao.repository;

import model.Order;
import java.sql.SQLException;
import java.util.List;

public interface OrderRepository extends EntytiRepository<Order> {
     List<Order> getByUserId(Integer user_id) throws SQLException;
}
