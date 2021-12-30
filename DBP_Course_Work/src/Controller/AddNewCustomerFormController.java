package Controller;

import DB.DbConnection;
import com.jfoenix.controls.JFXTextField;
import dao.CustomerDao;
import dao.CustomerDaoImpl;
import dto.CustomerDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//Cleared
public class AddNewCustomerFormController {
    public JFXTextField CustAddressTxt;
    public JFXTextField CustTitleTxt;
    public JFXTextField CustNameTxt;
    public JFXTextField CustCityTxt;
    public JFXTextField CustProvinceTxt;
    public JFXTextField PostalCodeTxt;
    public JFXTextField CustIdTxt;
    public AnchorPane AddnewCustomerContext;
    CustomerDao c1=new CustomerDaoImpl();

    public void SaveNewCustOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id=CustIdTxt.getText();
        String Title=CustTitleTxt.getText();
        String Name=CustNameTxt.getText();
        String Address=CustAddressTxt.getText();
        String City=CustCityTxt.getText();
        String Province=CustProvinceTxt.getText();
        String PostalCode=PostalCodeTxt.getText();
        if(c1.saveNewCustomer(new CustomerDTO(id,Title,Name,Address,City,Province,PostalCode))){
            new Alert(Alert.AlertType.CONFIRMATION,"Saved..").show();
        }
        else{
            new Alert(Alert.AlertType.WARNING,"Try Again..").show();
        }
    }
}
