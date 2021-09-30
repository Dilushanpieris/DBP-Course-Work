package Controller;

import DB.DbConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.OffsetDateTime;

public class DashBoardFormController {


    public JFXButton btnCashier;
    public Label lblTime;
    public JFXTextField txtUName;
    public JFXPasswordField txtPassword;
    public JFXCheckBox checkBoxAdmin;

    public void initialize() {
        TimeNow();
    }

    public void TimeNow() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            OffsetDateTime offsetDT = OffsetDateTime.now();
            lblTime.setText(offsetDT.toLocalDate() + " " + currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public void LoginButtonOnAction(ActionEvent actionEvent) throws IOException {
        if(checkBoxAdmin.isSelected()){
            if("admin".equals(txtUName.getText())&&"abc123".equals(txtPassword.getText())){
                //open Admin Form
                URL resource = getClass().getResource("../View/Admin/AdminForm.fxml");
                Parent load = FXMLLoader.load(resource);
                Scene scene=new Scene(load);
                Stage stage=new Stage();
                stage.setTitle("Admin Form");
                stage.setScene(scene);
                stage.show();
            }
            else{
                Alert alert=new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Data Error");
                alert.setHeaderText("Incorrect Username Or Password");
                alert.setContentText("Press Okay ");
                alert.showAndWait();
                return;
            }
        }
        else{
            if ("admin".equals(txtUName.getText())&&"abc123".equals(txtPassword.getText())){
                //open Cashier Form
                URL resource = getClass().getResource("../View/Cashier/CashierLoginForm.fxml");
                Parent load = FXMLLoader.load(resource);
                Scene scene=new Scene(load);
                Stage stage=new Stage();
                stage.setTitle("Admin Form");
                stage.setScene(scene);
                stage.show();
            }
            else{
                Alert alert=new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Data Error");
                alert.setHeaderText("Incorrect Username Or Password");
                alert.setContentText("Press Okay ");
                alert.showAndWait();
                return;
            }
        }
        if(txtUName.getText().isEmpty()||txtPassword.getText().isEmpty()){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Data Error");
            alert.setHeaderText("Incorrect Username Or Password");
            alert.setContentText("Press Okay ");
            alert.showAndWait();
            return;
        }
    }
}