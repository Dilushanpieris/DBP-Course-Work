package dao;

import Entity.OrderDetail;

import java.sql.SQLException;

public interface OrderDetailDao {
    boolean AddtoOrderDetail(OrderDetail od1) throws SQLException, ClassNotFoundException;

    boolean modifyOrder(OrderDetail od1) throws SQLException, ClassNotFoundException;

    boolean removeOrder(String tempItemID, String TempOrderID) throws SQLException, ClassNotFoundException;

    String[] getOrderDetailData(String tempItemID, String TempOrderID) throws SQLException, ClassNotFoundException;
}
