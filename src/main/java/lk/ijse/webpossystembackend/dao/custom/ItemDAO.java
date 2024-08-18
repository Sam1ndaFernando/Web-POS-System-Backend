package lk.ijse.webpossystembackend.dao.custom;

import lk.ijse.webpossystembackend.dao.CrudDAO;
import lk.ijse.webpossystembackend.entity.Item;

import java.sql.Connection;
import java.sql.SQLException;

public interface ItemDAO extends CrudDAO<Item, String> {

    Item get(Connection connection, String itemId) throws SQLException;
}