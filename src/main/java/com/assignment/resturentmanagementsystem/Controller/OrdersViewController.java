package com.assignment.resturentmanagementsystem.Controller;

import com.assignment.resturentmanagementsystem.bo.BoFactory;
import com.assignment.resturentmanagementsystem.bo.custom.*;
import com.assignment.resturentmanagementsystem.bo.custom.impl.OrderBoImpl;
import com.assignment.resturentmanagementsystem.Dto.*;
import com.assignment.resturentmanagementsystem.Dto.TM.CartTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrdersViewController implements Initializable {

    OrderViewBo orderViewBo = (OrderViewBo) BoFactory.getInstance().getSuperService(BoFactory.ServiceType.ORDERVIEW);
    CustomerBo customerBo = (CustomerBo) BoFactory.getInstance().getSuperService(BoFactory.ServiceType.CUSTOMER);
    FeedbackBo feedbackBo = (FeedbackBo) BoFactory.getInstance().getSuperService(BoFactory.ServiceType.FEEDBACK);
    MenuBo menuBo = (MenuBo) BoFactory.getInstance().getSuperService(BoFactory.ServiceType.MENU);

    public Button BtnReset;
    public Button BtnPlaceOrder;
    public Button BtnAddToCart;
    public Label price;
    public Label Itemname;
    public Label itemId;
    public Label cusname;
    public Label cusid;
    public Label orderdate;
    public Label orderID;
    public AnchorPane OrderviewAnc;
    public TableColumn colAction;
    public Button btnDelete;
    // public ComboBox Feedback;


    @FXML
    private ComboBox<String> cmbCustomerId;

    @FXML
    private ComboBox<String> cmbItemId;

    @FXML
    private TableColumn<CartTM, Button> colAction1;

    @FXML
    private TableColumn<CartTM, String> colItemId;

    @FXML
    private TableColumn<CartTM, String> colName;

    @FXML
    private TableColumn<CartTM, Double> colPrice;

    @FXML
    private TableColumn<CartTM, Integer> colQuantity;

    @FXML
    private TableColumn<CartTM, Double> colTotal;
    @FXML
    private TableColumn<CartTM, String> colType;

    @FXML
    private TableColumn<CartTM, String> colFee;


    @FXML
    private ComboBox<String> Feedback;


    @FXML
    private ComboBox<String> cmbType;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblItemName;

    @FXML
    private Label lblItemPrice;

    @FXML
    private Label lblOrderId;


    @FXML
    private Label orderDate;

    @FXML
    private TableView<CartTM> tblCart;
    @FXML
    private TextField txtAddToCartQty;

    @FXML
    private Button BtnSave;

    @FXML
    private Button BtnUpdate;

    OrderBo orderBo = new OrderBoImpl();


    private final ObservableList<CartTM> cartTMS = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            refreshPage();
            setCellValues();
            setCombo();
            getAllFeedbackIds();
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to load data").show();
        }
    }


//    MenuModel menuModel = new MenuModel();
//    CustomerModel customerModel = new CustomerModel();
//    OrderViewModel orderViewModel = new OrderViewModel();

    private void setCombo() {
        String[] type = {"Dine In", "Delivery", "cash on delivery"};
        ObservableList<String> types = FXCollections.observableArrayList();
        types.addAll(type);
        cmbType.setItems(types);
    }


    private void setCellValues() {
        // Set up the table columns with property values from CartTM class
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("cartQuantity"));
        colType.setCellValueFactory(new PropertyValueFactory<>("Type"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("FeedbackID"));
        colAction1.setCellValueFactory(new PropertyValueFactory<>("removeBtn"));


        // Bind the cart items observable list to the TableView
        tblCart.setItems(cartTMS);
    }


    private boolean refreshPage() throws SQLException, ClassNotFoundException {
        //    lblOrderId.setText(orderViewModel.getNextOrderId()+"");

        orderDate.setText(LocalDate.now().toString());
//        orderDate.setText(String.valueOf(LocalDate.now()));

        ArrayList<String> customerIds = customerBo.getAllCustomerIds();
        ObservableList<String> customerIdsTMS = FXCollections.observableArrayList();
        customerIdsTMS.addAll(customerIds);
        cmbCustomerId.setItems(customerIdsTMS);

        ArrayList<String> itemIds = menuBo.getAllItemIds();
        ObservableList<String> itemIdsTMS = FXCollections.observableArrayList();
        itemIdsTMS.addAll(itemIds);
        cmbItemId.setItems(itemIdsTMS);


        cmbCustomerId.getSelectionModel().clearSelection();
        cmbItemId.getSelectionModel().clearSelection();
        lblItemName.setText("");
        lblOrderId.setText("");
        lblItemPrice.setText("");
        txtAddToCartQty.setText("");
        lblCustomerName.setText("");
        cmbType.getSelectionModel().clearSelection();
        Feedback.getSelectionModel().clearSelection();
        getOrderId();


        cartTMS.clear();


        tblCart.refresh();


        return false;
    }


    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        System.out.println("add to cart");


        String selectedItemId = cmbItemId.getValue();

        if (selectedItemId == null) {
            new Alert(Alert.AlertType.ERROR, "Please select item..!").show();
            return; // Exit the method if no item is selected.
        }

        String cartQtyString = txtAddToCartQty.getText();

        String qtyPattern = "^[0-9]+$";


        if (!cartQtyString.matches(qtyPattern)) {
            new Alert(Alert.AlertType.ERROR, "Please enter valid quantity..!").show();
            return;
        }

        String Name = lblItemName.getText();
        int cartQty = Integer.parseInt(cartQtyString);
        String type = cmbType.getValue();
        String FeeId = Feedback.getValue();
        //  int Id = Integer.parseInt(lblOrderId.getText());
        int ItemId = Integer.parseInt(cmbItemId.getValue());


        double unitPrice = Double.parseDouble(lblItemPrice.getText());
        double total = unitPrice * cartQty;


        for (CartTM cartTM : cartTMS) {


            int selectedItemIdInt = Integer.parseInt(selectedItemId);
            if (cartTM.getItemId() == selectedItemIdInt) {


                int newQty = cartTM.getCartQuantity() + cartQty;
                cartTM.setCartQuantity(newQty);
                cartTM.setTotal(unitPrice * newQty);


                tblCart.refresh();
                return;
            }
        }


        Button btn = new Button("Remove");


//        int orderId = Integer.parseInt(lblOrderId.getText());
//        System.out.println(lblOrderId.getText());
        // System.out.println(Id);
        CartTM newCartTM = new CartTM(
                ItemId,
                Name,
                unitPrice,
                cartQty,
                total,
                FeeId,
                type,
                btn
        );


        btn.setOnAction(actionEvent -> {

            cartTMS.remove(newCartTM);


            tblCart.refresh();
        });


        cartTMS.add(newCartTM);

    }


    @FXML
    void btnResetOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        System.out.println("heloooo");
        refreshPage();
    }

    @FXML
    void cmbCustomerOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        if (cmbCustomerId.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "adoo!");
            return;
        }

        int selectedCustomerId = Integer.parseInt(cmbCustomerId.getValue());
        System.out.println(selectedCustomerId);
        CustomerDto customerDTO = customerBo.findById(selectedCustomerId);


        if (customerDTO != null) {
            lblCustomerName.setText(customerDTO.getCustomerName());
        }

    }

    @FXML
    void cmbItemOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {


//        int selectedItemId = Integer.parseInt(cmbItemId.getValue());
//        System.out.println("S"+selectedItemId);
//        MenuDTO menuDTO = menuModel.findById(String.valueOf(selectedItemId));
//
//            lblItemName.setText(menuDTO.getMenuName());
//            lblItemPrice.setText(String.valueOf(menuDTO.getPrice()));

        String selectedItemId = cmbItemId.getValue();

        // Null check to prevent NumberFormatException
        if (selectedItemId == null || selectedItemId.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please select a valid item!");
            return; // Exit the method if no item is selected
        }

        try {
            // Parse the selected item ID as an integer
            int selectedItemIdInt = Integer.parseInt(selectedItemId);

            // Retrieve menu details using the parsed item ID
            MenuDTO menuDTO = menuBo.findById(String.valueOf(selectedItemIdInt));

            // Update UI labels with retrieved menu details
            if (menuDTO != null) {
                lblItemName.setText(menuDTO.getMenuName());
                lblItemPrice.setText(String.valueOf(menuDTO.getPrice()));
            } else {
                new Alert(Alert.AlertType.ERROR, "No item found for the selected ID.").show();
            }
        } catch (NumberFormatException e) {
            // Handle invalid number format
            new Alert(Alert.AlertType.ERROR, "Invalid item ID format! Please select a valid item.").show();
        } catch (SQLException | ClassNotFoundException e) {
            // Handle potential database errors
            new Alert(Alert.AlertType.ERROR, "Error retrieving item details: " + e.getMessage()).show();
            throw e; // Re-throw the exception for further handling if needed
        }
    }

    public void cmbTypeOnAction(ActionEvent actionEvent) {

    }

    public void getOrderId() throws SQLException, ClassNotFoundException {



            try {
                int orderId = orderViewBo.getNextOrderId();
                lblOrderId.setText(String.valueOf(orderId));
                System.out.println(orderId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

    }

    public void cmbFeedbackOnAction(ActionEvent actionEvent) {

    }

    public void getAllFeedbackIds() {

        try {
            ObservableList<String> stringObservableList = feedbackBo.getAllFeedbackIds();
            Feedback.setItems(stringObservableList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


        int orderId = orderViewBo.getNextOrderId();
        System.out.println(orderId);
        String Type = String.valueOf(cmbType.getValue());
        int FeedbackID = Integer.parseInt(Feedback.getValue());
        int CustomerID = Integer.parseInt(cmbCustomerId.getValue());
        double Price = Double.parseDouble(lblItemPrice.getText());
        System.out.println(txtAddToCartQty.getText());

        int Quantity = Integer.parseInt(txtAddToCartQty.getText());
        int ItemId = Integer.parseInt(cmbItemId.getValue());

        if (Feedback.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "Ado").showAndWait();
            return;
        }


        OrderDTo orderDTo = new OrderDTo(
                orderId,
                Type,
                FeedbackID,
                CustomerID,
                Price,
                Quantity,
                ItemId

        );

        boolean isUpdate = orderBo.Save(orderDTo);
        if (isUpdate) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Order  save successfully...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Order not save successfully...!").show();
        }


    }

}

