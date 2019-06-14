package dao.repository;

import model.User;
import java.sql.SQLException;


public interface UserRepository extends EntytiRepository<User> {

    User getByName(String name) throws SQLException;
    User addByName(String name) throws SQLException;


}
