package lk.ijse.webpossystembackend.dao.custom.impl;

import lk.ijse.webpossystembackend.dao.SQLUtil;
import lk.ijse.webpossystembackend.dao.custom.OrderDetailsDAO;
import lk.ijse.webpossystembackend.entity.OrderDetails;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    private static final String GET_QUERY = "SELECT * FROM order_details";
    private static final String SAVE_QUERY = "INSERT INTO order_details(order_id, item_id, qty) VALUES (?, ?, ?)";

    @Override
    public List<OrderDetails> getAll(Connection connection) throws SQLException {
        ResultSet rs = SQLUtil.execute(connection, GET_QUERY);
        List<OrderDetails> details = new ArrayList<>();
        while (rs.next()){
            details.add(new OrderDetails(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getInt(3)
            ));
        }
        return details;
    }

    @Override
    public boolean save(Connection connection, OrderDetails dto) throws SQLException {
        return SQLUtil.execute(connection,
                SAVE_QUERY,
                dto.getOrderId(),
                dto.getItemId(),
                dto.getQty()
        );
    }

    @Override
    public boolean update(Connection connection, OrderDetails dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Connection connection, String s) throws SQLException {
        return false;
    }
}
