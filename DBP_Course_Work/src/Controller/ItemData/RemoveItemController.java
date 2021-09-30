package Controller.ItemData;

import DB.DbConnection;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RemoveItemController {
    public JFXTextField ItmCodeTxtField;
    public JFXTextField DescTxtField;
    public JFXTextField PackSizeTxtField;
    public JFXTextField UntPriceTxtField;
    public JFXTextField QtyOnHandTxtField;
    public AnchorPane RemoveItemContext;

    public void RemoveItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query="DELETE FROM Item WHERE ItemCode=?";
        PreparedStatement stm=con.prepareStatement(query);
        stm.setObject(1,ItmCodeTxtField.getText());
        if(stm.executeUpdate()>0){
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
        Connection con = DbConnection.getInstance().getConnection();
        String query="SELECT * FROM Item WHERE ItemCode=?";
        PreparedStatement stm=con.prepareStatement(query);
        stm.setObject(1,ItmCodeTxtField.getText());
        ResultSet set = stm.executeQuery();

        if(set.next()){
            String Tempcode=set.getString(1);
            String TempDes=set.getString(2);
            String TempPack=set.getString(3);
            double TempUnit=set.getDouble(4);
            int TempQtyOnHand=set.getInt(5);

            ItmCodeTxtField.setText(Tempcode);
            DescTxtField.setText(TempDes);
            PackSizeTxtField.setText(TempPack);
            UntPriceTxtField.setText(String.valueOf(TempUnit));
            QtyOnHandTxtField.setText(String.valueOf(TempQtyOnHand));
        }
        else {
            new Alert(Alert.AlertType.WARNING,"Empty Result Set").show();
        }

    }
}
