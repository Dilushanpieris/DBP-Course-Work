package dao;

import Entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDao {
    public List<String> getCustomerIds() throws SQLException, ClassNotFoundException;

    boolean saveNewCustomer(Customer c1) throws SQLException, ClassNotFoundException;

    String[] getCustData(String CustomerID) throws SQLException, ClassNotFoundException;
}
