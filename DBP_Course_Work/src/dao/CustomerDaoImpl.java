package dao;

import DB.DbConnection;
import Entity.Customer;
import dto.CustomerDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public  List<String> getCustomerIds() throws SQLException, ClassNotFoundException {
        ResultSet rst= DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Customer").executeQuery();
        List<String>ids=new ArrayList<>();
        while (rst.next()){
            ids.add(rst.getString(1)
            );
        }
        return ids;
    }
    @Override
    public boolean saveNewCustomer(Customer c1) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query="INSERT INTO Customer VALUES(?,?,?,?,?,?,?)";
        PreparedStatement stm=con.prepareStatement(query);
        stm.setObject(1,c1.getCustid());
        stm.setObject(2,c1.getCustTitle());
        stm.setObject(3,c1.getCustName());
        stm.setObject(4,c1.getCustAddress());
        stm.setObject(5,c1.getCity());
        stm.setObject(6,c1.getProvince());
        stm.setObject(7,c1.getPostalcode());
        return stm.executeUpdate()>0;
    }
    @Override
    public String[] getCustData(String CustomerID) throws SQLException, ClassNotFoundException {
        String data[]=new String[4];
        Connection con = DbConnection.getInstance().getConnection();
        String query="SELECT * FROM Customer WHERE CustID=?";
        PreparedStatement stm=con.prepareStatement(query);
        stm.setObject(1,CustomerID);
        ResultSet set = stm.executeQuery();
        if(set.next()) {
            data[0] = set.getString(3);
            data[1] = set.getString(4);
            data[2]= set.getString(5);
            data[3]= set.getString(7);
        }
        return data;
    }
}
