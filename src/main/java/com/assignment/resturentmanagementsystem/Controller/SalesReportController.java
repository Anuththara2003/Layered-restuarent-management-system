package com.assignment.resturentmanagementsystem.Controller;

import com.assignment.resturentmanagementsystem.bo.BoFactory;
import com.assignment.resturentmanagementsystem.bo.custom.SalesReportBo;
import com.assignment.resturentmanagementsystem.DBConnection.DBConnection;
import com.assignment.resturentmanagementsystem.Dto.SalesReportDTO;
import com.assignment.resturentmanagementsystem.Dto.TM.SalesReportTM;
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

public class SalesReportController implements Initializable {

    SalesReportBo salesReportBo = (SalesReportBo) BoFactory.getInstance().getSuperService(BoFactory.ServiceType.SALESREPORT);

    public void initialize(URL url, ResourceBundle resourceBundle) {
        colSle.setCellValueFactory(new PropertyValueFactory<>("SaleId"));
        colbranchid.setCellValueFactory(new PropertyValueFactory<>("BranchId"));

        try {
            refreshPage();
            loadTableData();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load SalesReport id").show();
        }
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<SalesReportDTO> salesReportDTOS = salesReportBo.getAll();

        ObservableList<SalesReportTM> salesReportTMS = FXCollections.observableArrayList();

        for (SalesReportDTO salesReportDTO : salesReportDTOS) {
            SalesReportTM salesReportTM = new SalesReportTM(
                    salesReportDTO.getSaleId(),
                    salesReportDTO.getBranchId()

            );
            salesReportTMS.add(salesReportTM);
        }
        tblSales.setItems(salesReportTMS);
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        loadTableData();

        btnSave.setDisable(false);

        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        txtsale.setText("");
        txtbranch.setText("");



    }

    @FXML
    private Button SalesReport;

    @FXML
    private AnchorPane ancSupplier;

    @FXML
    private Label branchid;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<SalesReportTM, Integer> colSle;

    @FXML
    private TableColumn<SalesReportTM, Integer> colbranchid;

    @FXML
    private Button reportBtn;

    @FXML
    private Label saleid;

    @FXML
    private TableView<SalesReportTM> tblSales;

    @FXML
    private TextField txtbranch;

    @FXML
    private TextField txtsale;

    @FXML
    private VBox vbox1;

    @FXML
    void deleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        String SaleId = txtsale.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = salesReportBo.Delete(Integer.parseInt(SaleId));
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "SalesReport  deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete SalesReport...!").show();
            }
        }
    }

    @FXML
    void onClickedTable(MouseEvent event) {


        SalesReportTM salesReportTM = (SalesReportTM) tblSales.getSelectionModel().getSelectedItem();
        if (salesReportTM != null){
            txtsale.setText(String.valueOf(salesReportTM.getSaleId()));
            txtbranch.setText(String.valueOf(salesReportTM.getBranchId()));




            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }

    @FXML
    void reportOnAction(ActionEvent event) {

        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass().getResourceAsStream("/Report/SalesReport.jrxml")
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


        int SaleId = Integer.parseInt(txtsale.getText());
        int BranchId = Integer.parseInt(txtbranch.getText());


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

        boolean isValidName =true;
        boolean isValidNic = true;
        boolean isValidEmail =true;
        boolean isValidPhone =true;

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
        SalesReportDTO salesReportDTO = new SalesReportDTO(
                SaleId,
                BranchId

        );

        boolean isSaved = salesReportBo.Save(salesReportDTO);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "SalesReport saved...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to save SalesReport...!").show();
        }
        //   }

    }

    @FXML
    void updateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        int SaleId = Integer.parseInt(txtsale.getText());
        int BranchId = Integer.parseInt(txtbranch.getText());




        SalesReportDTO salesReportDTO = new SalesReportDTO(
                SaleId,
                BranchId
          );


        boolean isUpdate = salesReportBo.update(salesReportDTO);
        if (isUpdate) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "SalesReport update...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to update SalesReport...!").show();
        }
    }


}
