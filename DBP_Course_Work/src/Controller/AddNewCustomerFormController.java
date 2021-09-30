package Controller;

import DB.DbConnection;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddNewCustomerFormController {
    public JFXTextField CustAddressTxt;
    public JFXTextField CustTitleTxt;
    public JFXTextField CustNameTxt;
    public JFXTextField CustCityTxt;
    public JFXTextField CustProvinceTxt;
    public JFXTextField PostalCodeTxt;
    public JFXTextField CustIdTxt;
    public AnchorPane AddnewCustomerContext;


    public void SaveNewCustOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id=CustIdTxt.getText();
        String Title=CustTitleTxt.getText();
        String Name=CustNameTxt.getText();
        String Address=CustAddressTxt.getText();
        String City=CustCityTxt.getText();
        String Province=CustProvinceTxt.getText();
        String PostalCode=PostalCodeTxt.getText();

        Connection con = DbConnection.getInstance().getConnection();
        String query="INSERT INTO Customer VALUES(?,?,?,?,?,?,?)";
        PreparedStatement stm=con.prepareStatement(query);

        stm.setObject(1,id);
        stm.setObject(2,Title);
        stm.setObject(3,Name);
        stm.setObject(4,Address);
        stm.setObject(5,City);
        stm.setObject(6,Province);
        stm.setObject(7,PostalCode);

        if(stm.executeUpdate()>0){
            new Alert(Alert.AlertType.CONFIRMATION,"Saved..").show();
        }
        else{
            new Alert(Alert.AlertType.WARNING,"Try Again..").show();
        }
    }
}
