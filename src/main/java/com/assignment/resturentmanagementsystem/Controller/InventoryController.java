package com.assignment.resturentmanagementsystem.Controller;

import com.assignment.resturentmanagementsystem.bo.BoFactory;
import com.assignment.resturentmanagementsystem.bo.custom.InventoryBo;
import com.assignment.resturentmanagementsystem.DBConnection.DBConnection;
import com.assignment.resturentmanagementsystem.Dto.InventoryDTO;
import com.assignment.resturentmanagementsystem.Dto.TM.InventoryTM;
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

public class InventoryController implements Initializable {

    InventoryBo inventoryBo = (InventoryBo) BoFactory.getInstance().getSuperService(BoFactory.ServiceType.INVENTORY);

    public void initialize(URL url, ResourceBundle resourceBundle) {
        colinventoryid.setCellValueFactory(new PropertyValueFactory<>("InventoryId"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colbranchid.setCellValueFactory(new PropertyValueFactory<>("BranchId"));

        try {
            refreshPage();
            loadTableData();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load Inventory id").show();
        }
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {

        ArrayList<InventoryDTO> inventoryDTOS = inventoryBo.getAll();

        ObservableList<InventoryTM> inventoryTMS = FXCollections.observableArrayList();

        for (InventoryDTO inventoryDTO : inventoryDTOS) {
            InventoryTM inventoryTM = new InventoryTM(
                    inventoryDTO.getInventoryId(),
                    inventoryDTO.getDescription(),
                    inventoryDTO.getBranchId()
            );
            inventoryTMS.add(inventoryTM);
        }
        tblinventory.setItems(inventoryTMS);
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        loadTableData();

        btnSave.setDisable(false);

        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        txtinventory.setText("");
        txtdesc.setText("");
        txtbranchid.setText("");



    }

    @FXML
    private Label Descriptiion;

    @FXML
    private Button Inventory;

    @FXML
    private AnchorPane InventoryAnc;

    @FXML
    private Label branchId;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<InventoryTM, String> colDescription;

    @FXML
    private TableColumn<InventoryTM, Integer> colbranchid;

    @FXML
    private TableColumn<InventoryTM, Integer> colinventoryid;

    @FXML
    private Label inventoryid;

    @FXML
    private Button reportBtn;

    @FXML
    private TableView<InventoryTM> tblinventory;

    @FXML
    private TextField txtbranchid;

    @FXML
    private TextField txtdesc;

    @FXML
    private TextField txtinventory;

    @FXML
    private VBox vbox1;

    @FXML
    void deleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        String InventoryId = txtinventory.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = inventoryBo.Delete(Integer.parseInt(InventoryId));
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Inventory  deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Inventory...!").show();
            }
        }
    }

    @FXML
    void onClickedTable(MouseEvent event) {


        InventoryTM inventoryTM = (InventoryTM) tblinventory.getSelectionModel().getSelectedItem();
        if (inventoryTM != null){
            txtinventory.setText(String.valueOf(inventoryTM.getInventoryId()));
            txtdesc.setText(inventoryTM.getDescription());
            txtbranchid.setText(String.valueOf(inventoryTM.getBranchId()));



            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }

    @FXML
    void reportOnAction(ActionEvent event) {

        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass().getResourceAsStream("/Report/InventoryReport.jrxml")
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


        int InventoryId = Integer.parseInt(txtinventory.getText());
        String Description = txtdesc.getText();
        int BranchId = Integer.parseInt(txtbranchid.getText());


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
        InventoryDTO inventoryDTO = new InventoryDTO(
                InventoryId,
                Description,
                BranchId


        );

        boolean isSaved = inventoryBo.Save(inventoryDTO);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Inventory saved...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to save Inventory...!").show();
        }
        //   }
    }

    @FXML
    void updateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        int InventoryId = Integer.parseInt(txtinventory.getText());
        String Description = txtdesc.getText();
        int BranchId = Integer.parseInt(txtbranchid.getText());





        InventoryDTO inventoryDTO = new InventoryDTO(
                InventoryId,
                Description,
                BranchId


        );

        boolean isUpdate = inventoryBo.update(inventoryDTO);
        if (isUpdate) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Inventory update...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to update Inventory...!").show();
        }
    }

}
