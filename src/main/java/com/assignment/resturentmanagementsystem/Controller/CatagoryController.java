package com.assignment.resturentmanagementsystem.Controller;

import com.assignment.resturentmanagementsystem.bo.BoFactory;
import com.assignment.resturentmanagementsystem.bo.custom.CatagoryBO;
import com.assignment.resturentmanagementsystem.DBConnection.DBConnection;
import com.assignment.resturentmanagementsystem.Dto.CategoryDTO;
import com.assignment.resturentmanagementsystem.Dto.TM.CategoryTM;
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

public class CatagoryController  implements Initializable {

 CatagoryBO catagoryBO = (CatagoryBO) BoFactory.getInstance().getSuperService(BoFactory.ServiceType.CATEGORY);

    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCatagoryId.setCellValueFactory(new PropertyValueFactory<>("CatID"));
        colname.setCellValueFactory(new PropertyValueFactory<>("CatName"));


        try {
            refreshPage();
            loadTableData();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load Category id").show();
        }
    }

   // CategoryModel categoryModel = new CategoryModel();
    private void loadTableData() throws SQLException, ClassNotFoundException {

        ArrayList<CategoryDTO> categoryDTOS = catagoryBO.getAll();

        ObservableList<CategoryTM> categoryTMS = FXCollections.observableArrayList();

        for (CategoryDTO categoryDTO : categoryDTOS) {
            CategoryTM categoryTM = new CategoryTM(
                    categoryDTO.getCatID(),
                    categoryDTO.getCatName()
            );
            categoryTMS.add(categoryTM);
        }
        tblCatagory.setItems(categoryTMS);
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {

        loadTableData();

        btnSave.setDisable(false);

        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        txttcatid.setText("");
        txtName.setText("");


    }

    @FXML
    private Button Catagory;

    @FXML
    private AnchorPane CategoryAnc;

    @FXML
    private Label CategoryID;

    @FXML
    private Label Name;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<CategoryTM, Integer> colCatagoryId;

    @FXML
    private TableColumn<CategoryTM, String> colname;

    @FXML
    private Button reportBtn;

    @FXML
    private TableView<CategoryTM> tblCatagory;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txttcatid;

    @FXML
    private VBox vbox1;

    @FXML
    void deleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        String CatID = txttcatid.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = catagoryBO.Delete(Integer.parseInt(CatID));
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Category  deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Category...!").show();
            }
        }
    }

    @FXML
    void onClickedTable(MouseEvent event) {


        CategoryTM categoryTM = (CategoryTM) tblCatagory.getSelectionModel().getSelectedItem();
        if (categoryTM != null){
            txttcatid.setText(String.valueOf(categoryTM.getCatID()));
            txtName.setText(categoryTM.getCatName());



            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }

    @FXML
    void reportOnAction(ActionEvent event) {

        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass().getResourceAsStream("/Report/CategoryReport.jrxml")
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


        int CatID = Integer.parseInt(txttcatid.getText());
        String CatName = txtName.getText();



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
        CategoryDTO categoryDTO = new CategoryDTO(
                CatID,
                CatName



        );

        boolean isSaved = catagoryBO.Save(categoryDTO);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Category saved...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to save Category...!").show();
        }
        //   }
    }

    @FXML
    void updateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        int CatID = Integer.parseInt(txttcatid.getText());
        String CatName = txtName.getText();




        CategoryDTO categoryDTO = new CategoryDTO(
                CatID,
                CatName


        );

        boolean isUpdate = catagoryBO.update(categoryDTO);
        if (isUpdate) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Category update...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to update Category...!").show();
        }
    }


}
