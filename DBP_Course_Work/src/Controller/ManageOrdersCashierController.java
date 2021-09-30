package Controller;

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

public class ManageOrdersCashierController {
    public AnchorPane CashierManageOrderContext;
    public JFXTextField lblItem;
    public JFXTextField lblOrderQty;
    public JFXTextField lblDiscount;
    public JFXTextField lblOrder;

    public void ModOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String TempOrderID=lblOrder.getText();
        String tempItemID=lblItem.getText();
        int tempOrderQty= Integer.parseInt(lblOrderQty.getText());
        double tempDis= Double.parseDouble(lblDiscount.getText());
        String query="UPDATE OrderDetail SET OrderQty=?,Discount=? WHERE ItemCode=? AND OrderID=?";
        PreparedStatement stm=con.prepareStatement(query);
        stm.setObject(1,tempOrderQty);
        stm.setObject(2,tempDis);
        stm.setObject(3,tempItemID);
        stm.setObject(4,TempOrderID);

        if(stm.executeUpdate()>0){
            new Alert(Alert.AlertType.CONFIRMATION,"Updated..").show();
        }
        else {
            new Alert(Alert.AlertType.WARNING,"TryAgain..").show();
        }
    }

    public void RmvOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String TempOrderID=lblOrder.getText();
        String tempItemID=lblItem.getText();
        int tempOrderQty= Integer.parseInt(lblOrderQty.getText());
        double tempDis= Double.parseDouble(lblDiscount.getText());
        String query="DELETE FROM OrderDetail WHERE ItemCode=? AND OrderID=?";
        PreparedStatement stm=con.prepareStatement(query);
        stm.setObject(1,tempItemID);
        stm.setObject(2,TempOrderID);
        if(stm.executeUpdate()>0){
            new Alert(Alert.AlertType.CONFIRMATION,"Deleted..").show();
        }
        else{
            new Alert(Alert.AlertType.WARNING,"Try Again..").show();
        }
    }

    public void CancelOnAction(ActionEvent actionEvent) {
        Stage stage1= (Stage) CashierManageOrderContext.getScene().getWindow();
        stage1.close();
    }

    public void OrderIdOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String TempOrderID=lblOrder.getText();
        String tempItemID=lblItem.getText();
        String query="SELECT * FROM OrderDetail WHERE ItemCode=? AND OrderID=?";
        PreparedStatement stm=con.prepareStatement(query);
        stm.setObject(1,tempItemID);
        stm.setObject(2,TempOrderID);
        ResultSet set = stm.executeQuery();
        if(set.next()){
            String TempItID=set.getString(1);
            String TempOdID=set.getString(2);
            int OrderQty=set.getInt(3);
            double TempDis=set.getDouble(4);

            lblItem.setText(TempItID);
            lblOrder.setText(TempOdID);
            lblOrderQty.setText(String.valueOf(OrderQty));
            lblDiscount.setText(String.valueOf(TempDis));
        }
        else {
            new Alert(Alert.AlertType.WARNING,"Empty Result Set").show();
        }
    }
}
