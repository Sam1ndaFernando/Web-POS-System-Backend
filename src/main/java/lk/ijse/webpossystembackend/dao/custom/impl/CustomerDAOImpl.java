package lk.ijse.webpossystembackend.dao.custom.impl;

import lk.ijse.webpossystembackend.dao.SQLUtil;
import lk.ijse.webpossystembackend.dao.custom.CustomerDAO;
import lk.ijse.webpossystembackend.entity.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    private static final String GET_QUERY = "SELECT * FROM customer";
    private static final String SAVE_QUERY = "INSERT INTO customer(customer_id, name, address, contact) VALUES (?,?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE customer SET name = ?, address = ?, contact = ? WHERE customer_id = ?";
    private static final String DELETE_QUERY = "DELETE FROM customer WHERE customer_id = ?";

    @Override
    public List<Customer> getAll(Connection connection) throws SQLException {
        ResultSet rs = SQLUtil.execute(connection, GET_QUERY);
        List<Customer> customers = new ArrayList<>();
        while (rs.next()){
            customers.add(new Customer(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4)
            ));
        }
        return customers;
    }

    @Override
    public boolean save(Connection connection, Customer dto) throws SQLException {
        return SQLUtil.execute(connection,
                SAVE_QUERY,
                dto.getCustomerId(),
                dto.getName(),
                dto.getAddress(),
                dto.getContact()
        );
    }

    @Override
    public boolean update(Connection connection, Customer dto) throws SQLException {
        return SQLUtil.execute(connection,
                UPDATE_QUERY,
                dto.getName(),
                dto.getAddress(),
                dto.getContact(),
                dto.getCustomerId()
        );
    }

    @Override
    public boolean delete(Connection connection, String customerId) throws SQLException {
        return SQLUtil.execute(connection, DELETE_QUERY, customerId);
    }

}