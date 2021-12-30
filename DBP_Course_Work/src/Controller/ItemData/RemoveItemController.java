package Controller.ItemData;

import DB.DbConnection;
import com.jfoenix.controls.JFXTextField;
import dao.ItemDao;
import dao.ItemDaoImpl;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//Cleared
public class RemoveItemController {
    public JFXTextField ItmCodeTxtField;
    public JFXTextField DescTxtField;
    public JFXTextField PackSizeTxtField;
    public JFXTextField UntPriceTxtField;
    public JFXTextField QtyOnHandTxtField;
    public AnchorPane RemoveItemContext;
    ItemDao i1=new ItemDaoImpl();

    public void RemoveItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if(i1.deleteItem(ItmCodeTxtField.getText())){

            new Alert(Alert.AlertType.CONFIRMATION,"Deleted..").show();
            Stage stage= (Stage) RemoveItemContext.getScene().getWindow();
            stage.close();
        }
        else{
            new Alert(Alert.AlertType.WARNING,"Try Again..").show();
        }
    }

    public void CancelOnAction(ActionEvent actionEvent) {
        Stage stage= (Stage) RemoveItemContext.getScene().getWindow();
        stage.close();
    }

    public void ItemCodeOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String[] data=i1.getItemData(ItmCodeTxtField.getText());
            ItmCodeTxtField.setText(data[0]);
            DescTxtField.setText(data[1]);
            PackSizeTxtField.setText(data[2]);
            UntPriceTxtField.setText(data[3]);
            QtyOnHandTxtField.setText(data[4]);
    }
}
