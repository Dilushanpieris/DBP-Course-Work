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

public class ModifyItemController {
    public AnchorPane ModifyItemContext;
    public JFXTextField ItmCodeTxtField;
    public JFXTextField DescTxtField;
    public JFXTextField PackSizeTxtField;
    public JFXTextField UntPriceTxtField;
    public JFXTextField QtyOnHandTxtField;

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

    public void ModifyItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String code=ItmCodeTxtField.getText();
        String Description=DescTxtField.getText();
        String PackSize=PackSizeTxtField.getText();
        double UnitPrice=Double.parseDouble(UntPriceTxtField.getText());
        int QtyOnHand=Integer.parseInt(QtyOnHandTxtField.getText());

        Connection con = DbConnection.getInstance().getConnection();
        String query="UPDATE Item SET Description=?,PackSize=?,UnitPrice=?,QtyOnHand=? WHERE ItemCode=?";
        PreparedStatement stm=con.prepareStatement(query);
        stm.setObject(1,Description);
        stm.setObject(2,PackSize);
        stm.setObject(3,UnitPrice);
        stm.setObject(4,QtyOnHand);
        stm.setObject(5,code);

        if(stm.executeUpdate()>0){
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
