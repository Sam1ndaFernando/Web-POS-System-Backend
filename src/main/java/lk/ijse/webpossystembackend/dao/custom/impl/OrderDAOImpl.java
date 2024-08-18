package lk.ijse.webpossystembackend.dao.custom.impl;

import lk.ijse.webpossystembackend.dao.SQLUtil;
import lk.ijse.webpossystembackend.dao.custom.OrderDAO;
import lk.ijse.webpossystembackend.entity.Order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    private static final String GET_QUERY = "SELECT * FROM `order`";
    private static final String SAVE_QUERY = "INSERT INTO `order`(order_id, date, discount, total, customer_id) VALUES (?, ?, ?, ?, ?)";

    @Override
    public List<Order> getAll(Connection connection) throws SQLException {
        ResultSet rs = SQLUtil.execute(connection, GET_QUERY);
        List<Order> orders = new ArrayList<>();
        while (rs.next()){
            orders.add(new Order(
                    rs.getString(1),
                    rs.getDate(2).toLocalDate(),
                    rs.getInt(3),
                    rs.getDouble(4),
                    rs.getString(5)
            ));
        }
        return orders;
    }

    @Override
    public boolean save(Connection connection, Order dto) throws SQLException {
        return SQLUtil.execute(connection,
                SAVE_QUERY,
                dto.getOrderId(),
                dto.getDate(),
                dto.getDiscount(),
                dto.getTotal(),
                dto.getCustomerId()
        );
    }

    @Override
    public boolean update(Connection connection, Order dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Connection connection, String s) throws SQLException {
        return false;
    }
}
