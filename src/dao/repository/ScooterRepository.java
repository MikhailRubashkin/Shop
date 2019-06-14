package dao.repository;


import model.Scooter;

import java.sql.SQLException;
import java.util.List;

public interface ScooterRepository extends EntytiRepository<Scooter> {
    //List<api.model.Scooter> getByOrderId(long orderId) throws SQLException;
    List<Scooter> getAll() throws SQLException;
}
