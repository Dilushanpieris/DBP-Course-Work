package Controller;

import DB.DbConnection;
import com.jfoenix.controls.JFXTextField;
import dao.OrderDetailDao;
import dao.OrderDetailDaoImpl;
import dto.OrderDetailDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//Cleared
public class ManageOrdersCashierController {
    public AnchorPane CashierManageOrderContext;
    public JFXTextField lblItem;
    public JFXTextField lblOrderQty;
    public JFXTextField lblDiscount;
    public JFXTextField lblOrder;
    OrderDetailDao od1=new OrderDetailDaoImpl();

    public void ModOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        int TempOrderID= Integer.parseInt(lblOrder.getText());
        String tempItemID=lblItem.getText();
        int tempOrderQty= Integer.parseInt(lblOrderQty.getText());
        double tempDis= Double.parseDouble(lblDiscount.getText());
        if(od1.modifyOrder(new OrderDetailDTO(tempItemID,TempOrderID,tempOrderQty,tempDis))){
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
        if(od1.removeOrder(tempItemID,TempOrderID)){
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
        String TempOrderID=lblOrder.getText();
        String tempItemID=lblItem.getText();
        String[] data=od1.getOrderDetailData(tempItemID,TempOrderID);
        lblItem.setText(data[0]);
        lblOrder.setText(data[1]);
        lblOrderQty.setText(data[2]);
        lblDiscount.setText(data[3]);
    }
}
