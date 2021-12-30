package dao;

import DB.DbConnection;
import Entity.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class OrderDaoImpl implements OrderDao{
    @Override
    public boolean OrderInsert(Order o1) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query="INSERT INTO Orders VALUES(?,?,?)";
        PreparedStatement stm=con.prepareStatement(query);
        stm.setObject(1,o1.getOrderID());
        stm.setObject(2,o1.getOrderDate());
        stm.setObject(3,o1.getCustID());
        return stm.executeUpdate()>0;
    }
    @Override
    public int getNextOrderID() throws SQLException, ClassNotFoundException {
        int tempOrderID= 1000 ;
        Connection con = DbConnection.getInstance().getConnection();
        String query="SELECT Max(OrderID) FROM Orders";
        PreparedStatement stm=con.prepareStatement(query);
        ResultSet set = stm.executeQuery();
        if(set.next()){
            if(set.getString(1)==null){
                tempOrderID=1000;
            }
            else{
                int lastID= Integer.parseInt(set.getString(1));
                tempOrderID=++lastID;

            }
        }
        return tempOrderID;
    }
}
