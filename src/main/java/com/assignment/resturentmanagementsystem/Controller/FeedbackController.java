package com.assignment.resturentmanagementsystem.Controller;

import com.assignment.resturentmanagementsystem.bo.BoFactory;
import com.assignment.resturentmanagementsystem.bo.custom.FeedbackBo;
import com.assignment.resturentmanagementsystem.DBConnection.DBConnection;
import com.assignment.resturentmanagementsystem.Dto.FeedbackDTO;
import com.assignment.resturentmanagementsystem.Dto.TM.FeedbackTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class FeedbackController implements Initializable {

    public TextField txtcustomerid;
    public TextField txtdesc;
    public Label Descriptiion;
    public TextField txtFeedback;

    FeedbackBo feedbackBo = (FeedbackBo) BoFactory.getInstance().getSuperService(BoFactory.ServiceType.FEEDBACK);

    public void initialize(URL url, ResourceBundle resourceBundle) {
        colFeedbackID.setCellValueFactory(new PropertyValueFactory<>("FeedbackID"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));


        try {
            refreshPage();
            loadTableData();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load Feedback id").show();
        }

    }



    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<FeedbackDTO> feedbackDTOS = feedbackBo.getAll();

        ObservableList<FeedbackTM> feedbackTMS = FXCollections.observableArrayList();

        for (FeedbackDTO feedbackDTO : feedbackDTOS) {
            FeedbackTM feedbackTM = new FeedbackTM(
                    feedbackDTO.getFeedbackID(),
                    feedbackDTO.getDescription(),
                    feedbackDTO.getCustomerID()
            );
            feedbackTMS.add(feedbackTM);
        }
        tblFeedback.setItems(feedbackTMS);


    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        loadTableData();

        btnSave.setDisable(false);

        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        txtFeedback.setText("");
        txtdesc.setText("");
        txtcustomerid.setText("");

    }

    @FXML
    private Label CustomerId;

    @FXML
    private Button Feedback;

    @FXML
    private Label FeedbackId;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<FeedbackTM, Integer> colCustomerID;

    @FXML
    private TableColumn<FeedbackTM, Integer> colDescription;

    @FXML
    private TableColumn<FeedbackTM, Integer> colFeedbackID;

    @FXML
    private TextField customerid;

    @FXML
    private TextField description;

    @FXML
    private TextField feedback;

    @FXML
    private Button reportBtn;

    @FXML
    private TableView<FeedbackTM> tblFeedback;

    @FXML
    private VBox vbox1;

    @FXML
    void deleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        String FeedbackID = txtFeedback.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = feedbackBo.Delete(Integer.parseInt(FeedbackID));
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Feedback  deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Feedback...!").show();
            }
        }


    }

    @FXML
    void onClickedFeedback(MouseEvent event) {

    }

    @FXML
    void onClickedTable(MouseEvent event) {

        FeedbackTM feedbackTm = (FeedbackTM) tblFeedback.getSelectionModel().getSelectedItem();
        if (feedbackTm != null){
            txtFeedback.setText(String.valueOf(feedbackTm.getFeedbackID()));
            txtdesc.setText(feedbackTm.getDescription());
            txtcustomerid.setText(String.valueOf(feedbackTm.getCustomerID()));


            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }

    }




    @FXML
    void reportOnAction(ActionEvent event) {

        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass().getResourceAsStream("/Report/Feedback.jrxml")
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

        int FeedbackID = Integer.parseInt(txtFeedback.getText());
        String Description = txtdesc.getText();
        int CustomerID = Integer.parseInt(txtcustomerid.getText());


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
        FeedbackDTO feedbackDTO = new FeedbackDTO(
                FeedbackID,
                Description,
                CustomerID

        );

        boolean isSaved = feedbackBo.Save(feedbackDTO);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Feedback saved...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to save Feedback...!").show();
        }
        //   }

    }



    @FXML
    void updateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {


        int FeedbackID = Integer.parseInt(txtFeedback.getText());
        String Description = txtdesc.getText();
        int CustomerID = Integer.parseInt(txtcustomerid.getText());


        FeedbackDTO feedbackDTO = new FeedbackDTO(
                FeedbackID,
                Description,
                CustomerID
        );

        boolean isUpdate = feedbackBo.update(feedbackDTO);
        if (isUpdate) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Feedback update...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to update Feedback...!").show();
        }
    }

}
