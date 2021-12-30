package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
//cleared
public class ManageItemsController {
    public AnchorPane MngItemsOnContext;

    public void RmvItemOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../View/Admin/ItemDataUI/RemoveItem.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene=new Scene(load);
        Stage stage=new Stage();
        stage.setTitle("Remove Item");
        stage.setScene(scene);
        stage.show();
    }

    public void ModItemOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../View/Admin/ItemDataUI/ModifyItem.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene=new Scene(load);
        Stage stage=new Stage();
        stage.setTitle("Remove Item");
        stage.setScene(scene);
        stage.show();
    }

    public void RegisterItemOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../View/Admin/ItemDataUI/RegisterNewItem.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene=new Scene(load);
        Stage stage=new Stage();
        stage.setTitle("Register New Item");
        stage.setScene(scene);
        stage.show();
    }

    public void GoBackOnAction(ActionEvent actionEvent) {
        Stage stage= (Stage) MngItemsOnContext.getScene().getWindow();
        stage.close();
    }
}
