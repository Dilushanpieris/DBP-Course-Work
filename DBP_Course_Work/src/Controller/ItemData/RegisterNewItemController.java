package Controller.ItemData;

import DB.DbConnection;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterNewItemController {
    public AnchorPane RegisterItemContext;
    public JFXTextField ItmCodeTxtField;
    public JFXTextField DescTxtField;
    public JFXTextField PackSizeTxtField;
    public JFXTextField UntPriceTxtField;
    public JFXTextField QtyOnHandTxtField;

    public void SaveItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String code=ItmCodeTxtField.getText();
        String Description=DescTxtField.getText();
        String PackSize=PackSizeTxtField.getText();
        double UnitPrice=Double.parseDouble(UntPriceTxtField.getText());
        int QtyOnHand=Integer.parseInt(QtyOnHandTxtField.getText());

            Connection con = DbConnection.getInstance().getConnection();
            String query="INSERT INTO Item VALUES(?,?,?,?,?)";
            PreparedStatement stm=con.prepareStatement(query);
            stm.setObject(1,code);
            stm.setObject(2,Description);
            stm.setObject(3,PackSize);
            stm.setObject(4,UnitPrice);
            stm.setObject(5,QtyOnHand);

            if(stm.executeUpdate()>0){
                new Alert(Alert.AlertType.CONFIRMATION,"Saved..").show();
                Stage stage= (Stage) RegisterItemContext.getScene().getWindow();
                stage.close();
            }
            else{
                new Alert(Alert.AlertType.WARNING,"Try Again..").show();
            }
    }

    public void CancelOnAction(ActionEvent actionEvent) {
        Stage stage= (Stage) RegisterItemContext.getScene().getWindow();
        stage.close();
    }
}
