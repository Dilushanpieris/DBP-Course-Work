package Controller.ItemData;

import DB.DbConnection;
import com.jfoenix.controls.JFXTextField;
import dao.ItemDao;
import dao.ItemDaoImpl;
import dto.ItemDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//Cleared
public class ModifyItemController {
    public AnchorPane ModifyItemContext;
    public JFXTextField ItmCodeTxtField;
    public JFXTextField DescTxtField;
    public JFXTextField PackSizeTxtField;
    public JFXTextField UntPriceTxtField;
    public JFXTextField QtyOnHandTxtField;
    ItemDao i1= new ItemDaoImpl();

    public void ItemCodeOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
            String[] data=i1.getItemData(ItmCodeTxtField.getText());
            ItmCodeTxtField.setText(data[0]);
            DescTxtField.setText(data[1]);
            PackSizeTxtField.setText(data[2]);
            UntPriceTxtField.setText(data[3]);
            QtyOnHandTxtField.setText(data[4]);
    }

    public void ModifyItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String code=ItmCodeTxtField.getText();
        String Description=DescTxtField.getText();
        String PackSize=PackSizeTxtField.getText();
        double UnitPrice=Double.parseDouble(UntPriceTxtField.getText());
        int QtyOnHand=Integer.parseInt(QtyOnHandTxtField.getText());

        if(i1.updateItemData(new ItemDTO(code,Description,PackSize,UnitPrice,QtyOnHand))){
            new Alert(Alert.AlertType.CONFIRMATION,"Updated..").show();
            Stage stage= (Stage) ModifyItemContext.getScene().getWindow();
            stage.close();
        }
        else{
            new Alert(Alert.AlertType.WARNING,"Try Again..").show();
        }
    }
    public void CancelOnAction(ActionEvent actionEvent) {
        Stage stage= (Stage) ModifyItemContext.getScene().getWindow();
        stage.close();
    }
}
