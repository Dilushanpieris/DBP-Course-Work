package dao;

import DB.DbConnection;
import Entity.OrderDetail;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailDaoImpl implements OrderDetailDao {
    @Override
    public boolean AddtoOrderDetail(OrderDetail od1) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query="INSERT INTO OrderDetail VALUES(?,?,?,?)";
        PreparedStatement stm=con.prepareStatement(query);
        stm.setObject(1,od1.getItemCode());
        stm.setObject(2,od1.getOrderId());
        stm.setObject(3,od1.getOrderQty());
        stm.setObject(4, od1.getDiscount());
        return stm.executeUpdate()>0;
    }
    @Override
    public boolean modifyOrder(OrderDetail od1) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query="UPDATE OrderDetail SET OrderQty=?,Discount=? WHERE ItemCode=? AND OrderID=?";
        PreparedStatement stm=con.prepareStatement(query);
        stm.setObject(1,od1.getOrderQty());
        stm.setObject(2,od1.getDiscount());
        stm.setObject(3, od1.getItemCode());
        stm.setObject(4,od1.getOrderId());
        return stm.executeUpdate()>0;
    }
    @Override
    public boolean removeOrder(String tempItemID,String TempOrderID) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query="DELETE FROM OrderDetail WHERE ItemCode=? AND OrderID=?";
        PreparedStatement stm=con.prepareStatement(query);
        stm.setObject(1,tempItemID);
        stm.setObject(2,TempOrderID);
        return stm.executeUpdate()>0;
    }
    @Override
    public String[] getOrderDetailData(String tempItemID,String TempOrderID) throws SQLException, ClassNotFoundException {
        String[] data=new String[4];
        Connection con = DbConnection.getInstance().getConnection();
        String query="SELECT * FROM OrderDetail WHERE ItemCode=? AND OrderID=?";
        PreparedStatement stm=con.prepareStatement(query);
        stm.setObject(1,tempItemID);
        stm.setObject(2,TempOrderID);
        ResultSet set = stm.executeQuery();
        if(set.next()){
            data[0]=set.getString(1);
            data[1]=set.getString(2);
            data[2]= String.valueOf(set.getInt(3));
            data[3]= String.valueOf(set.getDouble(4));
        }
        else {
            new Alert(Alert.AlertType.WARNING,"Empty Result Set").show();
        }
        return data;
    }
}
