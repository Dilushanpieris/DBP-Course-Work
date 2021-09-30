package Controller.DataController;

import DB.DbConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Item {
    public List<String> getAllItemIds() throws SQLException, ClassNotFoundException {
        ResultSet set = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Item").executeQuery();
        List<String> ids=new ArrayList<>();
        while (set.next()){
            ids.add(set.getString(1));
        }
        return ids;

    }
}
