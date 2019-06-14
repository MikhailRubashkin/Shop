package dao.repository;

import model.Bunch;

import java.sql.SQLException;
import java.util.List;


public interface BunchRepository extends EntytiRepository<Bunch> {
   // Product getByModelAndSupplier(String model, String supplier) throws SQLException;
   List<Bunch> getByOrderId(Integer order_id) throws SQLException;
}
