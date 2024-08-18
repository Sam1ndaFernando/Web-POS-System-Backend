package lk.ijse.webpossystembackend.bo.custom.impl;

import lk.ijse.webpossystembackend.bo.custom.OrderDetailsBO;
import lk.ijse.webpossystembackend.dao.DAOFactory;
import lk.ijse.webpossystembackend.dao.custom.ItemDAO;
import lk.ijse.webpossystembackend.dao.custom.OrderDetailsDAO;
import lk.ijse.webpossystembackend.dto.OrderDetailsDTO;
import lk.ijse.webpossystembackend.entity.Item;
import lk.ijse.webpossystembackend.entity.OrderDetails;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsBOImpl implements OrderDetailsBO {

    OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAILS);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public List<OrderDetailsDTO> getOrderDetails(Connection connection) throws SQLException {
        List<OrderDetails> details = orderDetailsDAO.getAll(connection);
        List<OrderDetailsDTO> detailsDTOS = new ArrayList<>();
        for (OrderDetails detail : details) {
            detailsDTOS.add(new OrderDetailsDTO(
                    detail.getOrderId(),
                    detail.getItemId(),
                    detail.getQty()
            ));
        }
        return detailsDTOS;
    }

    @Override
    public boolean saveOrderDetails(List<OrderDetailsDTO> details, Connection connection) throws SQLException {

        connection.setAutoCommit(false);

        for (OrderDetailsDTO detail : details) {

            if(!orderDetailsDAO.save(connection, new OrderDetails(detail.getOrderId(), detail.getItemId(), detail.getQty()))){
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            Item item = itemDAO.get(connection, detail.getItemId());
            if (item != null){
                item.setQty(item.getQty() - detail.getQty());
                if (!itemDAO.update(connection, item)){
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            }
        }

        connection.commit();
        connection.setAutoCommit(true);
        return true;
    }
}
