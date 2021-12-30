package dao;

import DB.DbConnection;
import Entity.Item;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao{
    @Override
    public List<String> getAllItemIds() throws SQLException, ClassNotFoundException {
        ResultSet set = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Item").executeQuery();
        List<String> ids=new ArrayList<>();
        while (set.next()){
            ids.add(set.getString(1));
        }
        return ids;
    }
    @Override
    public String[] getItemData(String id) throws SQLException, ClassNotFoundException {
        String[] itemData=new String[5];
        Connection con = DbConnection.getInstance().getConnection();
        String query="SELECT * FROM Item WHERE ItemCode=?";
        PreparedStatement stm=con.prepareStatement(query);
        stm.setObject(1,id);
        ResultSet set = stm.executeQuery();
        if(set.next()) {
           itemData[0]=set.getString(1);//id
           itemData[1] = set.getString(2);//description
           itemData[2]=set.getString(3);//packsize
           itemData[3] = String.valueOf(set.getDouble(4));//unitprice
           itemData[4] = String.valueOf(set.getInt(5));//QtyOnhand
        }
        else{
            new Alert(Alert.AlertType.WARNING,"Empty Result Set").show();
        }
        return itemData;
    }
    @Override
    public boolean updateItemData(Item i1) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query="UPDATE Item SET Description=?,PackSize=?,UnitPrice=?,QtyOnHand=? WHERE ItemCode=?";
        PreparedStatement stm=con.prepareStatement(query);
        stm.setObject(1,i1.getDescription());
        stm.setObject(2,i1.getPackSize());
        stm.setObject(3,i1.getUnitPrice());
        stm.setObject(4,i1.getQtyOnHand());
        stm.setObject(5,i1.getItemCode());
        return stm.executeUpdate()>0;
    }
    @Override
    public boolean saveNewItem(Item i1) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query="INSERT INTO Item VALUES(?,?,?,?,?)";
        PreparedStatement stm=con.prepareStatement(query);
        stm.setObject(1,i1.getItemCode());
        stm.setObject(2,i1.getDescription());
        stm.setObject(3,i1.getPackSize());
        stm.setObject(4,i1.getPackSize());
        stm.setObject(5,i1.getQtyOnHand());
        return stm.executeUpdate()>0;
    }
    @Override
    public boolean checkAlredyExists(String Id) throws SQLException, ClassNotFoundException {
        boolean x=false;
        Connection con = DbConnection.getInstance().getConnection();
        String query="SELECT * FROM Item WHERE ItemCode=?";
        PreparedStatement stm=con.prepareStatement(query);
        stm.setObject(1,Id);
        ResultSet set = stm.executeQuery();
        if(set.next()) {
           x=true;
        }
        return x;
    }
    @Override
    public boolean deleteItem(String ItemCode) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query="DELETE FROM Item WHERE ItemCode=?";
        PreparedStatement stm=con.prepareStatement(query);
        stm.setObject(1,ItemCode);
        return stm.executeUpdate()>0;
    }
}
