package lk.ijse.webpossystembackend.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T, ID> extends SuperDAO{

    List<T> getAll(Connection connection) throws SQLException;

    boolean save(Connection connection, T dto) throws SQLException;

    boolean update(Connection connection, T dto) throws SQLException;

    boolean delete(Connection connection, ID id) throws SQLException;

}
