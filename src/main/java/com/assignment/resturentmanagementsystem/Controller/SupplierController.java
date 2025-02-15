package com.assignment.resturentmanagementsystem.Controller;

import com.assignment.resturentmanagementsystem.bo.BoFactory;
import com.assignment.resturentmanagementsystem.bo.custom.SupplierBo;
import com.assignment.resturentmanagementsystem.DBConnection.DBConnection;
import com.assignment.resturentmanagementsystem.Dto.SupplierDTO;
import com.assignment.resturentmanagementsystem.Dto.TM.SupplierTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class SupplierController  implements Initializable {

    public Button supplier;
    public AnchorPane ancSupplier;
    public TextField txtsupid;
    public TextField txtName;

    SupplierBo supplierBo = (SupplierBo) BoFactory.getInstance().getSuperService(BoFactory.ServiceType.SUPPLIER);

    public void initialize(URL url, ResourceBundle resourceBundle) {
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colname.setCellValueFactory(new PropertyValueFactory<>("supplierName"));


        try {
            refreshPage();
            loadTableData();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load Supplier id").show();
        }
    }


    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<SupplierDTO> supplierDTOS = supplierBo.getAll();

        ObservableList<SupplierTM> supplierTMS = FXCollections.observableArrayList();

        for (SupplierDTO supplierDTO : supplierDTOS) {
            SupplierTM supplierTM = new SupplierTM(
                    supplierDTO.getSupplierId(),
                    supplierDTO.getSupplierName()
            );
            supplierTMS.add(supplierTM);
        }
        tblSupplier.setItems(supplierTMS);


    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        loadTableData();

        btnSave.setDisable(false);

        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        txtsupid.setText("");
        txtName.setText("");


    }

    @FXML
    private Button AddSupplier;

    @FXML
    private Label Name;

    @FXML
    private Button Supplier;

    @FXML
    private Label SupplierId;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<SupplierTM, Integer> colSupplierId;

    @FXML
    private TableColumn<SupplierTM, String> colname;

    @FXML
    private AnchorPane employee;

    @FXML
    private TextField name;

    @FXML
    private Button reportBtn;

    @FXML
    private TextField supID;

    @FXML
    private TableView<SupplierTM> tblSupplier;

    @FXML
    private VBox vbox1;

    @FXML
    void deleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {


        String supplierId = txtsupid.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = supplierBo.Delete(Integer.parseInt(supplierId));
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Supplier  deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Supplier...!").show();
            }
        }

    }

    @FXML
    void onClickedTable(MouseEvent event) {

        SupplierTM supplierTM = (SupplierTM) tblSupplier.getSelectionModel().getSelectedItem();
        if (supplierTM != null){
            txtsupid.setText(String.valueOf(supplierTM.getSupplierId()));
            txtName.setText(supplierTM.getSupplierName());



            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }

    }

    @FXML
    void onDelete(MouseEvent event) {

    }

    @FXML
    void onUpdate(MouseEvent event) {

    }

    @FXML
    void onclickedSave(MouseEvent event) {

    }

    @FXML
    void reportOnAction(ActionEvent event) {

        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass().getResourceAsStream("/Report/Supplier.jrxml")
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
            new Alert(Alert.AlertType.ERROR,"fail to generate report..").show();
        }

    }

    @FXML
    void saveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        int supplierId = Integer.parseInt(txtsupid.getText());
        String supplierName = txtName.getText();

//
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
        SupplierDTO supplierDTO = new SupplierDTO(
                supplierId,
                supplierName

        );

        boolean isSaved = supplierBo.Save(supplierDTO);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Supplier saved...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to save Supplier...!").show();
        }
        //   }

    }

    @FXML
    void updateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {


        int supplierId = Integer.parseInt(txtsupid.getText());
        String supplierName = txtName.getText();



        SupplierDTO supplierDTO = new SupplierDTO(
                supplierId,
                supplierName

        );

        boolean isUpdate = supplierBo.update(supplierDTO);
        if (isUpdate) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Supplier update...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to update Supplier...!").show();
        }


    }

    public void OnActionAddEmp(ActionEvent actionEvent) {
    }

    public void OnActionSupplier(ActionEvent actionEvent) {

    }
}
