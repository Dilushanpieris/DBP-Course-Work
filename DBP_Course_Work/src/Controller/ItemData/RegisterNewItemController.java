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
import java.sql.SQLException;
//Cleared
public class RegisterNewItemController {
    public AnchorPane RegisterItemContext;
    public JFXTextField ItmCodeTxtField;
    public JFXTextField DescTxtField;
    public JFXTextField PackSizeTxtField;
    public JFXTextField UntPriceTxtField;
    public JFXTextField QtyOnHandTxtField;
    ItemDao i1=new ItemDaoImpl();

    public void SaveItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String code=ItmCodeTxtField.getText();
        if(i1.checkAlredyExists(code)){
            new Alert(Alert.AlertType.WARNING,"Already existed ID..").show();
            return;
        }
        String Description=DescTxtField.getText();
        String PackSize=PackSizeTxtField.getText();
        double UnitPrice=Double.parseDouble(UntPriceTxtField.getText());
        int QtyOnHand=Integer.parseInt(QtyOnHandTxtField.getText());
            if(i1.saveNewItem(new ItemDTO(code,Description,PackSize,UnitPrice,QtyOnHand))){
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
