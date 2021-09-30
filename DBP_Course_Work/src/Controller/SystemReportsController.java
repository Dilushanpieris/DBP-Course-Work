package Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SystemReportsController {
    public AnchorPane SysReportsContext;


    public void IncomeReportsOnAction(ActionEvent actionEvent) {
    }

    public void CustWiseIncomeOnAction(ActionEvent actionEvent) {
    }

    public void MovableItemOnAction(ActionEvent actionEvent) {
    }

    public void leastMovableItem(ActionEvent actionEvent) {
    }
    public void LogoutAction(ActionEvent actionEvent) {
        Stage stage= (Stage) SysReportsContext.getScene().getWindow();
        stage.close();
    }
}
