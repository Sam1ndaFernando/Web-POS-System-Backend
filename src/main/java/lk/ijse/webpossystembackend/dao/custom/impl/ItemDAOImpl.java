package lk.ijse.webpossystembackend.dao.custom.impl;

import lk.ijse.webpossystembackend.dao.SQLUtil;
import lk.ijse.webpossystembackend.dao.custom.ItemDAO;
import lk.ijse.webpossystembackend.entity.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    private static final String GET_QUERY = "SELECT * FROM item";
    private static final String SAVE_QUERY = "INSERT INTO item(item_id, description, unit_price, qty) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE item SET description = ?, unit_price = ?, qty = ? WHERE item_id = ?";
    private static final String DELETE_QUERY = "DELETE FROM item WHERE item_id = ?";
    private static final String SELECT_QUERY = "SELECT * FROM item WHERE item_id = ?";

    @Override
    public List<Item> getAll(Connection connection) throws SQLException {
        ResultSet rs = SQLUtil.execute(connection, GET_QUERY);
        List<Item> items = new ArrayList<>();

        while (rs.next()){
            items.add(new Item(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getDouble(3),
                    rs.getInt(4)
            ));
        }
        return items;
    }

    @Override
    public boolean save(Connection connection, Item dto) throws SQLException {
        return SQLUtil.execute(connection,
                SAVE_QUERY,
                dto.getItemId(),
                dto.getDescription(),
                dto.getUnitPrice(),
                dto.getQty()
        );
    }

    @Override
    public boolean update(Connection connection, Item dto) throws SQLException {
        return SQLUtil.execute(connection,
                UPDATE_QUERY,
                dto.getDescription(),
                dto.getUnitPrice(),
                dto.getQty(),
                dto.getItemId()
        );
    }

    @Override
    public boolean delete(Connection connection, String s) throws SQLException {
        return SQLUtil.execute(connection, DELETE_QUERY, s);
    }

    @Override
    public Item get(Connection connection, String itemId) throws SQLException {
        ResultSet rs = SQLUtil.execute(connection, SELECT_QUERY, itemId);

        if (rs.next()){
            return new Item(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getDouble(3),
                    rs.getInt(4)
            );
        }
        return null;
    }
}