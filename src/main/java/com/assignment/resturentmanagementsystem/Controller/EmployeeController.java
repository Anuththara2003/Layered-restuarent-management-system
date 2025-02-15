package com.assignment.resturentmanagementsystem.Controller;

import com.assignment.resturentmanagementsystem.bo.BoFactory;
import com.assignment.resturentmanagementsystem.bo.custom.EmployeeBo;
import com.assignment.resturentmanagementsystem.dao.DaoFactory;
import com.assignment.resturentmanagementsystem.dao.custom.EmployeeDao;
import com.assignment.resturentmanagementsystem.DBConnection.DBConnection;
import com.assignment.resturentmanagementsystem.Dto.EmployeeDTO;
import com.assignment.resturentmanagementsystem.Dto.TM.EmployeeTM;
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

public class EmployeeController  implements Initializable {


    public TextField txtempID;
    public Label Name;
    public TextField txtname;

   EmployeeBo employeeBo = (EmployeeBo) BoFactory.getInstance().getSuperService(BoFactory.ServiceType.EMPLOYEE);
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colEmployeeID.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("empName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("employeeAddress"));

        try {
            refreshPage();
            loadTableData();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load Employee id").show();
        }
    }




    private void loadTableData() throws SQLException, ClassNotFoundException {

        ArrayList<EmployeeDTO> employeeDTOS = employeeBo.getAll();

        ObservableList<EmployeeTM> employeeTMS = FXCollections.observableArrayList();

        for (EmployeeDTO employeeDTO : employeeDTOS) {
            EmployeeTM employeeTM = new EmployeeTM(
                    employeeDTO.getEmployeeId(),
                    employeeDTO.getEmpName(),
                    employeeDTO.getEmployeeAddress()

            );
           // System.out.println(employeeTM);
            employeeTMS.add(employeeTM);
        }
        tblEmployee.setItems(employeeTMS);
    }



    private void refreshPage() throws SQLException, ClassNotFoundException {

        loadTableData();

        btnSave.setDisable(false);

        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        txtempID.setText("");
        txtname.setText("");
        txtAddress.setText("");


    }

    @FXML
    private Button AddEmployee;

    @FXML
    private Label Address;

    @FXML
    private Button Employee;

    @FXML
    private Label EmployeeID;

    @FXML
    private Label Type;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<EmployeeTM, String> colAddress;

    @FXML
    private TableColumn<EmployeeTM, Integer> colEmployeeID;

    @FXML
    private TableColumn<EmployeeTM, String> colName;

    @FXML
    private TextField empID;

    @FXML
    private AnchorPane employee;

    @FXML
    private TextField name;

    @FXML
    private Button reportBtn;

    @FXML
    private Button role;

    @FXML
    private TableView<EmployeeTM> tblEmployee;

    @FXML
    private TextField txtAddress;

    @FXML
    private VBox vbox1;

    @FXML
    void deleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        int employeeId = Integer.parseInt(txtempID.getText());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = employeeBo.Delete(employeeId);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Employee  deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Employee...!").show();
            }
        }

    }

    @FXML
    void onClickedTable(MouseEvent event) {


        EmployeeTM employeeTM = (EmployeeTM) tblEmployee.getSelectionModel().getSelectedItem();
        if (employeeTM != null){
            txtempID.setText(String.valueOf(employeeTM.getEmployeeId()));
            txtname.setText(employeeTM.getEmpName());
            txtAddress.setText(employeeTM.getEmployeeAddress());



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
                    getClass().getResourceAsStream("/Report/Employee.jrxml")
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


        int employeeId = Integer.parseInt(txtempID.getText());
        String employeeName = txtname.getText();
        String employeeAddress = txtAddress.getText();

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
        EmployeeDTO employeeDTO = new EmployeeDTO(
                employeeId,
                employeeName,
                employeeAddress
        );

        boolean isSaved = employeeBo.Save(employeeDTO);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Employee saved...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to save Employee...!").show();
        }
        //   }



    }

    @FXML
    void updateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {


        int employeeId = Integer.parseInt(txtempID.getText());
        String employeeName = txtname.getText();
        String employeeAddress = txtAddress.getText();



        EmployeeDTO employeeDTO = new EmployeeDTO(
                employeeId,
                employeeName,
                employeeAddress
        );

        boolean isUpdate = employeeBo.update(employeeDTO);
        if (isUpdate) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Employee update...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to update Employee...!").show();
        }
    }


    public void OnActionEmployee(ActionEvent actionEvent) {
    }

    public void OnActionAddEmp(ActionEvent actionEvent) {
        
    }

    public void Onactionrole(ActionEvent actionEvent) throws IOException {

        employee.getChildren().clear();
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/View/Role.fxml"));
        employee.getChildren().add(anchorPane);

    }
}
