package com.assignment.resturentmanagementsystem.Controller;

import com.assignment.resturentmanagementsystem.bo.BoFactory;
import com.assignment.resturentmanagementsystem.bo.custom.CustomerBo;
import com.assignment.resturentmanagementsystem.dao.DaoFactory;
import com.assignment.resturentmanagementsystem.dao.custom.CustomerDao;
import com.assignment.resturentmanagementsystem.dao.custom.impl.CustomerDaoImpl;
import com.assignment.resturentmanagementsystem.dao.CrudUtil;
import com.assignment.resturentmanagementsystem.DBConnection.DBConnection;
import com.assignment.resturentmanagementsystem.Dto.CustomerDto;
import com.assignment.resturentmanagementsystem.Dto.TM.CustomerTM;
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
import javafx.scene.layout.VBox;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

    public TableView tcistomerinfoTable;
    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public TextField txtage;
    public TextField txtaddress;
    public TextField txtname;
    public TextField cusid;
    public TableColumn colCustomerID;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colAge;
    public TableView tblCustomer;
    public Button order;


    @FXML
    private Button Customer;

    @FXML
    private Button Employee;

    @FXML
    private Label address;

    @FXML
    private TextField adminIdTxt;

    @FXML
    private Label age;

    @FXML
    private TextField capacityTxt;

    @FXML
    private Label custid;

    @FXML
    private Button deleteBtn;

    @FXML
    private Label name;

    @FXML
    private TextField nameTxt;

    @FXML
    private Button reportBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private TableView<?> trainInfoTable;

    @FXML
    private Button updateBtn;

    @FXML
    private VBox vbox1;


    CustomerBo customerBo = (CustomerBo) BoFactory.getInstance().getSuperService(BoFactory.ServiceType.CUSTOMER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("Age"));

        try {
            refreshPage();
            loadTableData();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load customer id").show();
        }

    }


    private void refreshPage() throws SQLException, ClassNotFoundException {
        loadNextCustomerId();
        loadTableData();

        btnSave.setDisable(false);

        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        cusid.setText("");
        txtname.setText("");
        txtaddress.setText("");
        txtage.setText("");
    }

    private void loadNextCustomerId() throws SQLException, ClassNotFoundException {

    }

    public AnchorPane cusanc;
    public TableColumn<CustomerTM, Integer> CustomerID;
    public TableColumn<CustomerTM, String> Name;
    public TableColumn<CustomerTM, String> Address;
    public TableColumn<CustomerTM, Integer> Age;




    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDto> customerDTOS = customerBo.getAll();

        ObservableList<CustomerTM> customerTMS = FXCollections.observableArrayList();

        for (CustomerDto customerDTO : customerDTOS) {
            CustomerTM customerTM = new CustomerTM(
                    customerDTO.getCustomerId(),
                    customerDTO.getCustomerName(),
                    customerDTO.getCustomerAddress(),
                    customerDTO.getCustomerAge()
            );
            customerTMS.add(customerTM);
        }
        tblCustomer.setItems(customerTMS);
    }

    @FXML
    void ClassManageOnAction(ActionEvent event) {

    }

    @FXML
    void TrainManageAction(ActionEvent event) {

    }

    @FXML
    void deleteOnAction(ActionEvent event) {

    }

    @FXML
    void reportOnAction(ActionEvent event) {


        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass().getResourceAsStream("/Report/CustomerReport.jrxml")
            );

            Connection connection = DBConnection.getInstance().getConnection();

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport,
                    null,
                    connection
            );

            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException | SQLException exception) {
            exception.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "fail to generate report..").show();
        }

    }

    @FXML
    void saveOnAction(ActionEvent event) {


    }

    @FXML
    void trainIdTxt(MouseEvent event) {

    }

    @FXML
    void updateOnAction(ActionEvent event) {

    }

    public void onClickedCus(ActionEvent actionEvent) {

    }

    public void onClickedEmp(MouseEvent mouseEvent) {
    }


    public void onclickedSave(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {

        int customerId = Integer.parseInt(cusid.getText());
        String name = txtname.getText();
        String Address = txtaddress.getText();
        int Age = Integer.parseInt(txtage.getText());


        txtname.setStyle(txtname.getStyle() + ";-fx-border-color: #7367F0;");
        txtaddress.setStyle(txtaddress.getStyle() + ";-fx-border-color: #7367F0;");
        txtage.setStyle(txtage.getStyle() + ";-fx-border-color: #7367F0;");

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

        if (!isValidName) {
            System.out.println(txtname.getStyle());
            txtname.setStyle(txtname.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid name.............");
//            return;
        }

        if (!isValidNic) {
            txtaddress.setStyle(txtaddress.getStyle() + ";-fx-border-color: red;");
//            return;
        }

        if (!isValidEmail) {
            txtage.setStyle(txtage.getStyle() + ";-fx-border-color: red;");
        }

//        if (!isValidPhone){
//            Fee_Id.setStyle(Fee_Id.getStyle()+";-fx-border-color: red;");
//        }

        //if (isValidName && isValidNic && isValidEmail && isValidPhone) {
        CustomerDto customerDTO = new CustomerDto(
                customerId,
                name,
                Address,
                Age
        );

        boolean isSaved = customerBo.Save(customerDTO);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Customer saved...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to save customer...!").show();
        }
        //   }


    }

    @FXML
    public void onClickedTable(MouseEvent mouseEvent) {

        CustomerTM customerTM = (CustomerTM) tblCustomer.getSelectionModel().getSelectedItem();
        if (customerTM != null) {
            cusid.setText(String.valueOf(customerTM.getCustomerId()));
            txtname.setText(customerTM.getName());
            txtaddress.setText(customerTM.getAddress());
            txtage.setText(String.valueOf(customerTM.getAge()));


            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }

    public void onUpdate(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {

        int customerId = Integer.parseInt(cusid.getText());
        String Name = txtname.getText();
        String Address = txtaddress.getText();
        int Age = Integer.parseInt(txtage.getText());


        CustomerDto customerDTO = new CustomerDto(
                customerId,
                Name,
                Address,
                Age
        );

        boolean isUpdate = customerBo.update(customerDTO);
        if (isUpdate) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Customer update...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to update customer...!").show();
        }

    }

    public boolean onDelete(int Cust_Id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from customer where customer_id=?", Cust_Id);
    }


    public void onDelete(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        int customerId = Integer.parseInt(cusid.getText());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = customerBo.Delete(customerId);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Customer deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete customer...!").show();
            }
        }
    }

    public void OnActionCusOrder(ActionEvent actionEvent) throws IOException {

        cusanc.getChildren().clear();
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/View/Order.fxml"));
        cusanc.getChildren().add(anchorPane);

    }

}



