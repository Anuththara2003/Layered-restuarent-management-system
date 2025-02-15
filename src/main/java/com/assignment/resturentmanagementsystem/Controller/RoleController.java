package com.assignment.resturentmanagementsystem.Controller;

import com.assignment.resturentmanagementsystem.bo.BoFactory;
import com.assignment.resturentmanagementsystem.bo.custom.RoleBo;
import com.assignment.resturentmanagementsystem.DBConnection.DBConnection;
import com.assignment.resturentmanagementsystem.Dto.RoleDTO;
import com.assignment.resturentmanagementsystem.Dto.TM.RoleTM;
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

public class RoleController implements Initializable {

    RoleBo roleBo = (RoleBo) BoFactory.getInstance().getSuperService(BoFactory.ServiceType.ROLE);

    public void initialize(URL url, ResourceBundle resourceBundle) {
        colRole.setCellValueFactory(new PropertyValueFactory<>("roleId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("roleName"));
        colEmployeeID.setCellValueFactory(new PropertyValueFactory<>("EmployeeId"));


        try {
            refreshPage();
            loadTableData();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load Role id").show();
        }
    }


    private void loadTableData() throws SQLException, ClassNotFoundException {

        ArrayList<RoleDTO> roleDTOS = roleBo.getAll();

        ObservableList<RoleTM> roleTMS = FXCollections.observableArrayList();

        for (RoleDTO roleDTO : roleDTOS) {
            RoleTM roleTM = new RoleTM(
                    roleDTO.getRoleId(),
                    roleDTO.getRoleName(),
                    roleDTO.getEmployeeId()
            );
            roleTMS.add(roleTM);
        }
        tblRole.setItems(roleTMS);

    }

    private void refreshPage() throws SQLException, ClassNotFoundException {

        loadTableData();

        btnSave.setDisable(false);

        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        txtrole.setText("");
        txtname.setText("");
        txtemployeeid.setText("");



    }

    @FXML
    private Button Role;

    @FXML
    private AnchorPane RolePane;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<RoleTM, Integer> colEmployeeID;

    @FXML
    private TableColumn<RoleTM, String> colName;

    @FXML
    private TableColumn<RoleTM, Integer> colRole;

    @FXML
    private Label employeeid;

    @FXML
    private Label name;

    @FXML
    private Button reportBtn;

    @FXML
    private Label roleid;

    @FXML
    private TableView<RoleTM> tblRole;

    @FXML
    private TextField txtemployeeid;

    @FXML
    private TextField txtname;

    @FXML
    private TextField txtrole;

    @FXML
    private VBox vbox1;

    @FXML
    void deleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        String roleId = txtrole.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = roleBo.Delete(Integer.parseInt(roleId));
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Role  deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Role...!").show();
            }
        }
    }

    @FXML
    void onClickedTable(MouseEvent event) {

        RoleTM roleTM = (RoleTM) tblRole.getSelectionModel().getSelectedItem();
        if (roleTM != null){
            txtrole.setText(String.valueOf(roleTM.getEmployeeId()));
            txtname.setText(roleTM.getRoleName());
            txtemployeeid.setText(String.valueOf(roleTM.getEmployeeId()));



            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }

    @FXML
    void reportOnAction(ActionEvent event) {

        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass().getResourceAsStream("/Report/EmployeeRole.jrxml")
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


        int roleId = Integer.parseInt(txtrole.getText());
        String roleName = txtname.getText();
        int EmployeeId = Integer.parseInt(txtemployeeid.getText());



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
        RoleDTO roleDTO = new RoleDTO(
                roleId,
                roleName,
                EmployeeId



        );

        boolean isSaved = roleBo.Save(roleDTO);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Role saved...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to save Role...!").show();
        }
        //   }
    }

    @FXML
    void updateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {


        int roleId = Integer.parseInt(txtrole.getText());
        String roleName = txtname.getText();
        int EmployeeId = Integer.parseInt(txtemployeeid.getText());




        RoleDTO roleDTO = new RoleDTO(
                roleId,
                roleName,
                EmployeeId


        );

        boolean isUpdate = roleBo.update(roleDTO);
        if (isUpdate) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Role update...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to update Role...!").show();
        }
    }


}
