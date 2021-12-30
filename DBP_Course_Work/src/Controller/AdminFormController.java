package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
//Cleared
public class AdminFormController {
    public AnchorPane AdminContext;

    public void LogoutOnAction(ActionEvent actionEvent) throws IOException {
        AdminContext.getScene().getWindow().hide();
        URL resource = getClass().getResource("../View/DashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene=new Scene(load);
        Stage stage=new Stage();
        stage.setTitle("Main Login");
        stage.setScene(scene);
        stage.show();

    }

    public void SysReportsOnAction(ActionEvent actionEvent) throws IOException {
        //Open Reports Tab
        URL resource = getClass().getResource("../View/Admin/SystemReports.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene=new Scene(load);
        Stage stage=new Stage();
        stage.setTitle("System Reports");
        stage.setScene(scene);
        stage.show();
    }

    public void manageItemsOnAction(ActionEvent actionEvent) throws IOException {
        //Items Tab
        URL resource = getClass().getResource("../View/Admin/ManageItems.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene=new Scene(load);
        Stage stage=new Stage();
        stage.setTitle("System Reports");
        stage.setScene(scene);
        stage.show();
    }
}
