package lk.ijse.webpossystembackend.bo.custom.impl;

import lk.ijse.webpossystembackend.bo.custom.OrderBO;
import lk.ijse.webpossystembackend.dao.DAOFactory;
import lk.ijse.webpossystembackend.dao.custom.OrderDAO;
import lk.ijse.webpossystembackend.dto.OrderDTO;
import lk.ijse.webpossystembackend.entity.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderBOImpl implements OrderBO {

    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);

    @Override
    public boolean saveOrder(OrderDTO orderDTO, Connection connection) throws SQLException {
        return orderDAO.save(connection, new Order(
                orderDTO.getOrderId(),
                orderDTO.getDate(),
                orderDTO.getDiscount(),
                orderDTO.getTotal(),
                orderDTO.getCustomerId()
        ));
    }

    @Override
    public List<OrderDTO> getAllOrder(Connection connection) throws SQLException {
        List<Order> orders = orderDAO.getAll(connection);
        List<OrderDTO> orderDTOS = new ArrayList<>();
        for (Order order : orders) {
            orderDTOS.add(new OrderDTO(
                    order.getOrderId(),
                    order.getDate(),
                    order.getDiscount(),
                    order.getTotal(),
                    order.getCustomerId()
            ));
        }
        return  orderDTOS;
    }
}
