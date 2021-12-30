package Controller;

import dto.CartTm;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.*;
import dto.OrderDTO;
import dto.OrderDetailDTO;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
//Cleared



public class MakeCustomerOrderController {
    public AnchorPane placeOrderContext;
    public JFXComboBox<String> CustIdComboBox;
    public Label lblOrderID;
    public Label lblOrderDate;
    public Label lblOrderTime;
    public JFXTextField CustAddressTXT;
    public JFXTextField CustNameTxt;
    public JFXTextField CustCityTxt;
    public JFXTextField CustPostCodeTxt;
    public JFXComboBox<String> ItmIdCmbBox;
    public JFXTextField ItmDesc;
    public JFXTextField UnitPriceTxt;
    public JFXTextField QtyOnHandTxt;
    public JFXTextField QTYTXT;
    public TableView<CartTm> tblViewContext;
    public TableColumn ItmCodeCol;
    public TableColumn DescriptorCol;
    public TableColumn QTYCol;
    public TableColumn UnitPriceCol;
    public TableColumn TotalCol;
    public Label lblTotal;

    int cartSelectedRow=-1;
    CustomerDaoImpl c1=new CustomerDaoImpl();
    ItemDao i1=new ItemDaoImpl();
    OrderDao o1=new OrderDaoImpl();
    OrderDetailDao od1=new OrderDetailDaoImpl();

    public void initialize() throws SQLException, ClassNotFoundException {
        ItmCodeCol.setCellValueFactory(new PropertyValueFactory<>("ItmCode"));
        DescriptorCol.setCellValueFactory(new PropertyValueFactory<>("Description"));
        QTYCol.setCellValueFactory(new PropertyValueFactory<>("qty"));
        UnitPriceCol.setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
        TotalCol.setCellValueFactory(new PropertyValueFactory<>("total"));
        LoadDateAndTime();
        try {
            loadCustIDs();
            loadItemCodes();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        CustIdComboBox.getItems().add("Add_Customer");

        tblViewContext.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cartSelectedRow=(int)newValue;
        });
    }

    public void setCustomerData(String CustomerID) throws SQLException, ClassNotFoundException {
            String data[]=c1.getCustData(CustomerID);
            CustNameTxt.setText(data[0]);
            CustAddressTXT.setText(data[1]);
            CustCityTxt.setText(data[2]);
            CustPostCodeTxt.setText(data[3]);
    }

    public void CustIdCmbBoxOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        if(CustIdComboBox.getValue()=="Add_Customer"){
            URL resource = getClass().getResource("../View/Cashier/AddNewCustomerForm.fxml");
            Parent load = FXMLLoader.load(resource);
            Scene scene=new Scene(load);
            Stage stage=new Stage();
            stage.setTitle("Add Customer");
            stage.setScene(scene);
            stage.show();
        }
        else{
            setCustomerData(CustIdComboBox.getSelectionModel().getSelectedItem());
        }
    }

    public void ItmIDCmbBoxOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        setItemData(ItmIdCmbBox.getSelectionModel().getSelectedItem());
    }

    private void setItemData(String ItemID) throws SQLException, ClassNotFoundException {
            String[]Data=i1.getItemData(ItemID);
            ItmDesc.setText(Data[1]);
            UnitPriceTxt.setText(Data[3]);
            QtyOnHandTxt.setText(Data[4]);
    }

    public void ClearOnAction(ActionEvent actionEvent) {
        if(cartSelectedRow==-1){
            new Alert(Alert.AlertType.WARNING,"Please Select A Row").show();
        }
        else{
            obList.remove(cartSelectedRow);
            tblViewContext.refresh();
        }

    }
    ObservableList<CartTm>obList= FXCollections.observableArrayList();

    public void AddToCartOnAction(ActionEvent actionEvent) {
        if(QTYTXT.getText().isEmpty()){
            new Alert(Alert.AlertType.WARNING,"Please Add QTY").show();
            return;
        }
        String description=ItmDesc.getText();
        int QtyOnHand=Integer.parseInt(QtyOnHandTxt.getText());
        double unitPrice=Double.parseDouble(UnitPriceTxt.getText());
        int qtyforCust=Integer.parseInt(QTYTXT.getText());
        double total=unitPrice*qtyforCust;

        if(QtyOnHand<qtyforCust){
            new Alert(Alert.AlertType.WARNING,"Invalid QTY").show();
            return;
        }
        CartTm cartTm=new CartTm(
                ItmIdCmbBox.getValue(),
                description,
                qtyforCust,
                unitPrice,
                total
        );
        int rowNumber=isExists(cartTm);
        if(rowNumber==-1){
            obList.add(cartTm);
        }
        else{
           CartTm tempTm=obList.get(rowNumber);
           CartTm newTm=new CartTm(
                   tempTm.getItmCode(),
                   tempTm.getDescription(),
                   tempTm.getQty()+qtyforCust,
                   unitPrice,
                   total+tempTm.getTotal()
           );
            if(QtyOnHand<tempTm.getQty()){
                new Alert(Alert.AlertType.WARNING,"Invalid QTY").show();
                return;
            }

           obList.remove(rowNumber);
           obList.add(newTm);
        }
        tblViewContext.setItems(obList);
        calculateCost();

    }
    void calculateCost(){
        double ttl=0;
        for (CartTm tm:obList
             ) {
            ttl+=tm.getTotal();
        }
        lblTotal.setText(ttl+"/=");
    }
    public int CalQty(){
        int ttl=0;
        for (CartTm tm:obList
        ) {
            ttl+=tm.getQty();
        }
        return ttl;
    }

    private int isExists(CartTm tm) {
        for (int i = 0; i < obList.size(); i++) {
            if (tm.getItmCode() == obList.get(i).getItmCode()) {
                return i;
            }
        }
        return  -1;
    }

    public void AddToOrderDetail(int orderID) throws SQLException, ClassNotFoundException, IOException {
        if(od1.AddtoOrderDetail(new OrderDetailDTO(ItmIdCmbBox.getValue(),orderID,CalQty(),0.00))){
            new Alert(Alert.AlertType.CONFIRMATION,"Order Placed..").showAndWait();
            RefreshOverride();
        }
        else{
            new Alert(Alert.AlertType.WARNING,"Try Again..").show();
        }

    }


    public void PlaceOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        if(CustIdComboBox.getSelectionModel().isEmpty()&ItmIdCmbBox.getSelectionModel().isEmpty()){
            new Alert(Alert.AlertType.WARNING,"Please Select Data And Try Again..").show();
            return;
        }
        int orderID=o1.getNextOrderID();
        OffsetDateTime offsetDT = OffsetDateTime.now();
        if(o1.OrderInsert(new OrderDTO(orderID,offsetDT.toLocalDate(),CustIdComboBox.getValue()))){
        }
        else{
            new Alert(Alert.AlertType.WARNING,"Try Again..").show();
        }
        AddToOrderDetail(orderID);
    }

    private void loadItemCodes() throws SQLException, ClassNotFoundException {
        List<String>ItemIds=i1.getAllItemIds();
        ItmIdCmbBox.getItems().addAll(ItemIds);
    }


    private void loadCustIDs() throws SQLException, ClassNotFoundException {
        List<String> customerIds = c1.getCustomerIds();
        CustIdComboBox.getItems().addAll(customerIds);

    }

    private void LoadDateAndTime(){
        Date date=new Date();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-DD");
        lblOrderDate.setText(format.format(date));

        Timeline time=new Timeline(new KeyFrame(Duration.ZERO,e->{
            LocalTime currentTime=LocalTime.now();
            lblOrderTime.setText(
                    currentTime.getHour()+":"+currentTime.getMinute()+":"+currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
                );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    public void RefreshAction(ActionEvent actionEvent) throws IOException {
        Stage stage1= (Stage) placeOrderContext.getScene().getWindow();
        stage1.close();

        URL resource = getClass().getResource("../View/Cashier/MakeCustomerOrder.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene=new Scene(load);
        Stage stage=new Stage();
        stage.setTitle("Place Order");
        stage.setScene(scene);
        stage.show();
    }
    public void RefreshOverride() throws IOException {
        Stage stage1= (Stage) placeOrderContext.getScene().getWindow();
        stage1.close();

        URL resource = getClass().getResource("../View/Cashier/MakeCustomerOrder.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene=new Scene(load);
        Stage stage=new Stage();
        stage.setTitle("Place Order");
        stage.setScene(scene);
        stage.show();
    }
}
