package dao;

import Entity.Order;

import java.sql.SQLException;
import java.time.LocalDate;

public interface OrderDao {

    boolean OrderInsert(Order o1) throws SQLException, ClassNotFoundException;

    int getNextOrderID() throws SQLException, ClassNotFoundException;
}
