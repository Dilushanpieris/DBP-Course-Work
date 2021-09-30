package Controller.DataController;

import DB.DbConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    public List<String> getCustomerIds() throws SQLException, ClassNotFoundException {
        ResultSet rst=DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Customer").executeQuery();
        List<String>ids=new ArrayList<>();
        while (rst.next()){
           ids.add(rst.getString(1)
           );
        }
        return ids;
    }
}
