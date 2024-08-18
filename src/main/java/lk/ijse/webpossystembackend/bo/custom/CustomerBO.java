package lk.ijse.webpossystembackend.bo.custom;

import lk.ijse.webpossystembackend.bo.SuperBO;
import lk.ijse.webpossystembackend.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO {
    boolean createCustomer(CustomerDTO customerDTO, Connection connection) throws SQLException;

    boolean updateCustomer(CustomerDTO customerDTO, Connection connection) throws SQLException;

    boolean deleteCustomer(String customerId, Connection connection) throws SQLException;

    List<CustomerDTO> getAllCustomers(Connection connection) throws SQLException;
}
