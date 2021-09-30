package Controller;

import Controller.DataController.Customer;
import Controller.DataController.Item;
import DB.DbConnection;
import Model.CartTm;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.sun.org.apache.xpath.internal.operations.Or;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;




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
    static int OrderId=1002;
    public static int FiristOrderID=1000;


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
        Connection con = DbConnection.getInstance().getConnection();
        String query="SELECT * FROM Customer WHERE CustID=?";
        PreparedStatement stm=con.prepareStatement(query);
        stm.setObject(1,CustomerID);
        ResultSet set = stm.executeQuery();

        if(set.next()){
            String TempID=set.getString(1);
            String TempTitle=set.getString(2);
            String TempName=set.getString(3);
            String TempAddress=set.getString(4);
            String TempCity=set.getString(5);
            String TempProvince=set.getString(6);
            String TempPostalCode=set.getString(7);

            CustNameTxt.setText(TempName);
            CustAddressTXT.setText(TempAddress);
            CustCityTxt.setText(TempCity);
            CustPostCodeTxt.setText(TempPostalCode);
        }
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
        Connection con = DbConnection.getInstance().getConnection();
        String query="SELECT * FROM Item WHERE ItemCode=?";
        PreparedStatement stm=con.prepareStatement(query);
        stm.setObject(1,ItemID);
        ResultSet set = stm.executeQuery();

        if(set.next()){
            String TempCode=set.getString(1);
            String TempDescription=set.getString(2);
            String TempPackSize=set.getString(3);
            Double TempUnitPrice=set.getDouble(4);
            int TempQtyOnHand=set.getInt(5);

            ItmDesc.setText(TempDescription);
            UnitPriceTxt.setText(String.valueOf(TempUnitPrice));
            QtyOnHandTxt.setText(String.valueOf(TempQtyOnHand));
        }
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

    public void AddToOrderDetail() throws SQLException, ClassNotFoundException, IOException {
        Connection con = DbConnection.getInstance().getConnection();
        String query="INSERT INTO OrderDetail VALUES(?,?,?,?)";
        PreparedStatement stm=con.prepareStatement(query);
        stm.setObject(1,ItmIdCmbBox.getValue());
        stm.setObject(2,FiristOrderID);
        stm.setObject(3,CalQty());
        stm.setObject(4,0.00);
        if(stm.executeUpdate()>0){
            new Alert(Alert.AlertType.CONFIRMATION,"Order Placed..").showAndWait();
            FiristOrderID=getFinalOrderID()+1;
            RefreshOverride();
        }
        else{
            new Alert(Alert.AlertType.WARNING,"Try Again..").show();
        }

    }
    public int getFinalOrderID() throws SQLException, ClassNotFoundException {
        String tempOrderID="1001";
        Connection con = DbConnection.getInstance().getConnection();
        String query="SELECT Max(OrderID) FROM Orders";
        PreparedStatement stm=con.prepareStatement(query);
        ResultSet set = stm.executeQuery();
        if(set.next()){
            tempOrderID=set.getString(1);
        }
        return Integer.parseInt(tempOrderID);
    }

    public void PlaceOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        OffsetDateTime offsetDT = OffsetDateTime.now();
        Connection con = DbConnection.getInstance().getConnection();
        String query="INSERT INTO Orders VALUES(?,?,?)";
        PreparedStatement stm=con.prepareStatement(query);
        stm.setObject(1,FiristOrderID);
        stm.setObject(2,offsetDT.toLocalDate());
        stm.setObject(3,CustIdComboBox.getValue());
        if(stm.executeUpdate()>0){
        }
        else{
            new Alert(Alert.AlertType.WARNING,"Try Again..").show();
        }
        AddToOrderDetail();





    }

    private void loadItemCodes() throws SQLException, ClassNotFoundException {
        List<String>ItemIds=new Item().getAllItemIds();
        ItmIdCmbBox.getItems().addAll(ItemIds);
    }


    private void loadCustIDs() throws SQLException, ClassNotFoundException {
        List<String> customerIds = new Customer().getCustomerIds();
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
