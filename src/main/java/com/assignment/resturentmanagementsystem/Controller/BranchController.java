package com.assignment.resturentmanagementsystem.Controller;

import com.assignment.resturentmanagementsystem.bo.BoFactory;
import com.assignment.resturentmanagementsystem.bo.custom.BranchBo;
import com.assignment.resturentmanagementsystem.dao.custom.BranchDao;
import com.assignment.resturentmanagementsystem.dao.custom.impl.BranchDaoImpl;
import com.assignment.resturentmanagementsystem.DBConnection.DBConnection;
import com.assignment.resturentmanagementsystem.Dto.BranchDto;
import com.assignment.resturentmanagementsystem.Dto.TM.BranchTM;
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



public class BranchController implements Initializable {


    public TextField txtname;
    public TextField txtbranchid;

    @FXML
    private Button Branch;

    @FXML
    private Label EmployeeID;

    @FXML
    private Button Inventory;

    @FXML
    private Label Location;

    @FXML
    private TextField Name;

    @FXML
    private Button SalesReport;

    @FXML
    private AnchorPane branch;

    @FXML
    private Label branchid;

    @FXML
    private TextField bronchi;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<BranchTM, Integer> colBranchID;

    @FXML
    private TableColumn<BranchTM, Integer> colEmployeeID;

    @FXML
    private TableColumn<BranchTM, String> colLocation;

    @FXML
    private TableColumn<BranchTM, String> colName;

    @FXML
    private Label name;

    @FXML
    private Button reportBtn;

    @FXML
    private TableView<BranchTM> tblBranch;

    @FXML
    private TextField txtempid;


    @FXML
    private TextField txtLocation;

    @FXML
    private VBox vbox1;


    BranchBo branchBo= (BranchBo) BoFactory.getInstance().getSuperService(BoFactory.ServiceType.BRANCH);

    public void initialize(URL url, ResourceBundle resourceBundle) {
        colBranchID.setCellValueFactory(new PropertyValueFactory<>("branchId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("branchName"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("branchAddress"));
        colEmployeeID.setCellValueFactory(new PropertyValueFactory<>("employeeId"));

        try {
            refreshPage();
            loadTableData();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load Branch id").show();
        }

    }



    private void refreshPage() throws SQLException, ClassNotFoundException {


        loadTableData();

        btnSave.setDisable(false);

        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        txtbranchid.setText("");
        txtname.setText("");
        txtLocation.setText("");
        txtempid.setText("");

    }



    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<BranchDto> branchDtos = branchBo.getAll();

        ObservableList<BranchTM> branchTMS = FXCollections.observableArrayList();

        for (BranchDto branchDto : branchDtos) {
            BranchTM branchTM = new BranchTM(
                    branchDto.getBranchId(),
                    branchDto.getBranchName(),
                    branchDto.getBranchAddress(),
                    branchDto.getEmployeeId()
            );
            branchTMS.add(branchTM);
        }
        tblBranch.setItems(branchTMS);
    }

    @FXML
    void OnClickrdInventory(MouseEvent event) {

    }

    @FXML
    void OnclickedInventory(ActionEvent event) {

    }

    @FXML
    void SalesReportOnAction(ActionEvent event) throws IOException {
        branch.getChildren().clear();
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/View/salesReport.fxml"));
        branch.getChildren().add(anchorPane);

    }

    @FXML
    void deleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {


        //System.out.println("haiyooooo");
        String branchID = txtbranchid.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = branchBo.Delete(Integer.parseInt(branchID));
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Branch  deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete branch...!").show();
            }
        }

    }

    @FXML
    void onClickedBranch(MouseEvent event) {

    }

    @FXML
    void onClickedSalesreport(MouseEvent event) {

    }

    @FXML
    void onClickedTable(MouseEvent event) {

        BranchTM branchTM = (BranchTM) tblBranch.getSelectionModel().getSelectedItem();
        if (branchTM != null){
            txtbranchid.setText(String.valueOf(branchTM.getBranchId()));
            txtname.setText(branchTM.getBranchName());
            txtLocation.setText(branchTM.getBranchAddress());
            txtempid.setText(String.valueOf(branchTM.getEmployeeId()));


            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }


    }

    @FXML
    void onClickedbranch(ActionEvent event) {

    }





@FXML
void saveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {


    int branchID = Integer.parseInt(txtbranchid.getText());
    String Name = txtname.getText();
    String Location = txtLocation.getText();
    int EmployeeID = Integer.parseInt(txtempid.getText());


    boolean isValidName =true;
    boolean isValidNic = true;
    boolean isValidEmail =true;
    boolean isValidPhone =true;

    BranchDto branchDto = new BranchDto(
            branchID,
            Name,
            Location,
            EmployeeID
    );

    boolean isSaved = branchBo.Save(branchDto);
    if (isSaved) {
        refreshPage();
        new Alert(Alert.AlertType.INFORMATION, "Branch saved...!").show();
    } else {
        new Alert(Alert.AlertType.ERROR, "Fail to save Branch...!").show();
    }


}

@FXML
void txtName(MouseEvent event) {

}

    public void reportOnAction(ActionEvent actionEvent) {


        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass().getResourceAsStream("/Report/BranchReport.jrxml")
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

    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


        int branchID = Integer.parseInt(txtbranchid.getText());
        String Name = txtname.getText();
        String Location = txtLocation.getText();
        int EmployeeID = Integer.parseInt(txtempid.getText());


        BranchDto branchDto = new BranchDto(
                branchID,
                Name,
                Location,
                EmployeeID
        );

        boolean isUpdate = branchBo.update(branchDto);
        if (isUpdate) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Branch update...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to update Branch...!").show();
        }
    }

    public void OnActionInventory(ActionEvent actionEvent) throws IOException {
        branch.getChildren().clear();
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/View/Inventory.fxml"));
        branch.getChildren().add(anchorPane);

    }
}
