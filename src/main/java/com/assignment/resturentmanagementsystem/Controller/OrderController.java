package com.assignment.resturentmanagementsystem.Controller;

import com.assignment.resturentmanagementsystem.bo.BoFactory;
import com.assignment.resturentmanagementsystem.bo.custom.OrderBo;
import com.assignment.resturentmanagementsystem.Dto.OrderDTo;
import com.assignment.resturentmanagementsystem.Dto.TM.OrderTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class OrderController implements Initializable {
    public Button orderView;
    public AnchorPane Order;
    public Button Reservation;
    public Button Branch;
    public TableColumn colitemid;
    public TableColumn colprice;
    public TableColumn colQty;


    OrderBo orderBo = (OrderBo) BoFactory.getInstance().getSuperService(BoFactory.ServiceType.ORDER);

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        colOrderID.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colType.setCellValueFactory(new PropertyValueFactory<>("Type"));
        colFeedbackID.setCellValueFactory(new PropertyValueFactory<>("FeedbackID"));
        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        colitemid.setCellValueFactory(new PropertyValueFactory<>("ItemId"));
        colprice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("Quantity"));

        try {
            refreshPage();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    public TableView<OrderTm> tblOrder;
    public TextField txtname;
    public TextField txtorder;
    public TextField txtcusid;
    public TextField txttype;


    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;


    @FXML
    private Button BtnDelete;

    @FXML
    private TextField txtOrderId;

    @FXML
    private TableColumn<OrderTm, Integer> colCustomerID;

    @FXML
    private TableColumn<OrderTm, Integer> colFeedbackID;

    @FXML
    private TableColumn<OrderTm, Integer> colOrderID;

    @FXML
    private TableColumn<OrderTm, String> colType;
    @FXML
    private Button BtnSave;

    @FXML
    private Button BtnUpdate;

    @FXML
    private TextField txtFeedback;




    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<OrderDTo> orderDTos = orderBo.getAll();
        System.out.println(orderDTos);
        ObservableList<OrderTm> ordersTms = FXCollections.observableArrayList();

        for (OrderDTo orderDTo : orderDTos) {
            OrderTm orderTm = new OrderTm(
                    orderDTo.getOrderId(),
                    orderDTo.getType(),
                    orderDTo.getFeedbackID(),
                    orderDTo.getCustomerID(),
                    orderDTo.getItemId(),
                    orderDTo.getPrice(),
                    orderDTo.getQuantity()
            );
            ordersTms.add(orderTm);
        }
        tblOrder.setItems(ordersTms);
    }


    @FXML
    void OnClickedTable(MouseEvent event) {

    }

    @FXML
    void OnclickedTable(ActionEvent event) {

    }

    @FXML
    void ReservationOnAction(ActionEvent event) throws IOException {
        Order.getChildren().clear();
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/View/Reservation.fxml"));
        Order.getChildren().add(anchorPane);


    }

    @FXML
    void deleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {


        String OrderID = txtorder.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = orderBo.Delete(Integer.parseInt(OrderID));
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "order  deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete order...!").show();
            }
        }

    }

    @FXML
    void onClickedOrder(MouseEvent event) {

    }

    @FXML
    void onClickedReservation(MouseEvent event) {

    }

    @FXML
    void onClickedTable(MouseEvent event) {


        OrderTm orderTm = tblOrder.getSelectionModel().getSelectedItem();
        if (orderTm != null) {
            txtOrderId.setText(String.valueOf(orderTm.getOrderId()));
//            txtorder.setText(String.valueOf(orderTm.getOrderId()));
//            txttype.setText(orderTm.getType());
//            txtFeedback.setText(String.valueOf(orderTm.getFeedbackID()));
//            txtcusid.setText(String.valueOf(orderTm.getCustomerID()));


            //btnSave.setDisable(true);

            BtnDelete.setDisable(false);
            // btnUpdate.setDisable(false);
        }


    }

    @FXML
    void saveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {


        int OrderID = Integer.parseInt(txtorder.getText());
        String Type = txttype.getText();
        int FeedbackID = Integer.parseInt(txtFeedback.getText());
        int CustomerID = Integer.parseInt(txtcusid.getText());
        int ItemId = Integer.parseInt(txtcusid.getText());
        double Price = Double.parseDouble(txtcusid.getText());
        int Quantity = Integer.parseInt(txtcusid.getText());


//    bronchi.setStyle(bronchi.getStyle()+";-fx-border-color: #7367F0;");
//    Location.setStyle(Location.getStyle()+";-fx-border-color: #7367F0;");
//    EmployeeID.setStyle(EmployeeID.getStyle()+";-fx-border-color: #7367F0;");

//
//        String namePattern = "^[A-Za-z ]+$";
//        String nicPattern = "^[0-9]{9}[vVxX]||[0-9]{12}$";
//        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
//        String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";
//
//        boolean isValidName = name.matches(namePattern);
//        boolean isValidNic = nic.matches(nicPattern);
//        boolean isValidEmail = Address.matches(emailPattern);
//        boolean isValidPhone = phone.matches(phonePattern);

        boolean isValidName = true;
        boolean isValidNic = true;
        boolean isValidEmail = true;
        boolean isValidPhone = true;

//    if (!isValidName){
//        System.out.println(Name.getSt);
//        Name.setStyle(Name.getStyle()+";-fx-border-color: red;");
//        System.out.println("Invalid name.............");
////            return;
//    }
//
//    if (!isValidNic){
//        txtaddress.setStyle(txtaddress.getStyle()+";-fx-border-color: red;");
////            return;
//    }
//
//    if (!isValidEmail){
//        txtage.setStyle(txtage.getStyle()+";-fx-border-color: red;");
//    }

//        if (!isValidPhone){
//            Fee_Id.setStyle(Fee_Id.getStyle()+";-fx-border-color: red;");
//        }

        //if (isValidName && isValidNic && isValidEmail && isValidPhone) {
        OrderDTo orderDTo = new OrderDTo(
                OrderID,
                Type,
                FeedbackID,
                CustomerID,
                Price,
                ItemId,
                Quantity

        );

        boolean isSaved = orderBo.Save(orderDTo);
        if (isSaved) {
            refreshPage();
            //   loadTableData();
            new Alert(Alert.AlertType.INFORMATION, "Order saved...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to save order...!").show();
        }
        //   }


    }


//    @FXML
//    void updateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
//
//       // System.out.println("heloooooo");
//
//        int OrderID = Integer.parseInt(txtorder.getText());
//        String Type = txttype.getText();
//        int FeedbackID = Integer.parseInt(txtFeedback.getText());
//        int CustomerID = Integer.parseInt(txtcusid.getText());
//        int ItemId = Integer.parseInt(txtcusid.getText());
//        double Price = Double.parseDouble(txtcusid.getText());
//        int Quantity = Integer.parseInt(txtcusid.getText());
//
//
//        OrderDTo orderDTo = new OrderDTo(
//                OrderID,
//                Type,
//                FeedbackID,
//                CustomerID,
//                Price,
//                ItemId,
//                Quantity
//        );
//
//        boolean isUpdate = OrderModel.updateOnAction(orderDTo);
//        if (isUpdate) {
//            refreshPage();
//            new Alert(Alert.AlertType.INFORMATION, "Order update...!").show();
//        } else {
//            new Alert(Alert.AlertType.ERROR, "Fail to update order...!").show();
//        }
//    }

    private void refreshPage() throws SQLException, ClassNotFoundException {

        loadTableData();

        // btnSave.setDisable(false);

        //  btnUpdate.setDisable(true);
        //  BtnDelete.setDisable(true);

        //  txtorder.setText("");
        // txttype.setText("");
        //   txtFeedback.setText("");
        //   txtcusid.setText("");


    }

    public void OnActionOrderView(ActionEvent actionEvent) throws IOException {

        Order.getChildren().clear();
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/View/OrdersView.fxml"));
        Order.getChildren().add(anchorPane);


    }


    public void OnDeleteAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        int orderId = Integer.parseInt(txtOrderId.getText());

        boolean isDelete = orderBo.Delete(orderId);

        if (isDelete) {
            new Alert(Alert.AlertType.INFORMATION, "Order  delete successfully...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Order not delete successfully...!").show();
        }

        // tblOrder.refresh();
        refreshPage();
    }
}
