package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
//cleaerd
public class CashierLoginFormController {
    public AnchorPane CashierLoginFormContext;

    public void ManageOrdersOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../View/Cashier/ManageOrdersCashier.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene=new Scene(load);
        Stage stage=new Stage();
        stage.setTitle("Manage Orders");
        stage.setScene(scene);
        stage.show();
    }

    public void MakeCustOrderOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../View/Cashier/MakeCustomerOrder.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene=new Scene(load);
        Stage stage=new Stage();
        stage.setTitle("Place Order");
        stage.setScene(scene);
        stage.show();
    }

    public void LogoutOnAction(ActionEvent actionEvent) throws IOException {
        CashierLoginFormContext.getScene().getWindow().hide();
        URL resource = getClass().getResource("../View/DashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene=new Scene(load);
        Stage stage=new Stage();
        stage.setTitle("Main Login");
        stage.setScene(scene);
        stage.show();
    }
}
