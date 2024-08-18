package lk.ijse.webpossystembackend.bo.custom.impl;

import lk.ijse.webpossystembackend.bo.custom.CustomerBO;
import lk.ijse.webpossystembackend.dao.DAOFactory;
import lk.ijse.webpossystembackend.dao.custom.CustomerDAO;
import lk.ijse.webpossystembackend.dto.CustomerDTO;
import lk.ijse.webpossystembackend.entity.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public boolean createCustomer(CustomerDTO customerDTO, Connection connection) throws SQLException {
        return customerDAO.save(connection, new Customer(
                customerDTO.getCustomerId(),
                customerDTO.getName(),
                customerDTO.getAddress(),
                customerDTO.getContact()
        ));
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO, Connection connection) throws SQLException {
        return customerDAO.update(connection, new Customer(
                customerDTO.getCustomerId(),
                customerDTO.getName(),
                customerDTO.getAddress(),
                customerDTO.getContact()
        ));
    }

    @Override
    public boolean deleteCustomer(String customerId, Connection connection) throws SQLException {
        return customerDAO.delete(connection, customerId);
    }

    @Override
    public List<CustomerDTO> getAllCustomers(Connection connection) throws SQLException {
        List<Customer> customers = customerDAO.getAll(connection);
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        for (Customer customer : customers) {
            customerDTOS.add(new CustomerDTO(
                    customer.getCustomerId(),
                    customer.getName(),
                    customer.getAddress(),
                    customer.getContact()
            ));
        }
        return customerDTOS;
    }
}
