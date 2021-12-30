package dao;

import Entity.Item;

import java.sql.SQLException;
import java.util.List;

public interface ItemDao {
    List<String> getAllItemIds() throws SQLException, ClassNotFoundException;

    String[] getItemData(String id) throws SQLException, ClassNotFoundException;

    boolean updateItemData(Item i1) throws SQLException, ClassNotFoundException;

    boolean saveNewItem(Item i1) throws SQLException, ClassNotFoundException;

    boolean checkAlredyExists(String Id) throws SQLException, ClassNotFoundException;

    boolean deleteItem(String ItemCode) throws SQLException, ClassNotFoundException;
}
