package com.assignment.resturentmanagementsystem.Controller;

import com.assignment.resturentmanagementsystem.bo.BoFactory;
import com.assignment.resturentmanagementsystem.bo.custom.ReservationBO;
import com.assignment.resturentmanagementsystem.Dto.ResevationDTO;
import com.assignment.resturentmanagementsystem.Dto.TM.ReservationTM;
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

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ReservationController  implements Initializable {

    ReservationBO reservationBO = (ReservationBO) BoFactory.getInstance().getSuperService(BoFactory.ServiceType.RESERVATION);


    public TableView tblreservation;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        colreservationId.setCellValueFactory(new PropertyValueFactory<>("reservationId"));
        colcustid.setCellValueFactory(new PropertyValueFactory<>("CustomerId"));
        coldescription.setCellValueFactory(new PropertyValueFactory<>("Description"));

        try {
            refreshPage();
            loadTableData();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load Reservation id").show();
        }

    }



    private void loadTableData() throws SQLException, ClassNotFoundException {

        ArrayList<ResevationDTO> resevationDTOS = reservationBO.getAll();

        ObservableList<ReservationTM> reservationTMS = FXCollections.observableArrayList();

        for (ResevationDTO resevationDTO : resevationDTOS) {
            ReservationTM reservationTM = new ReservationTM(
                    resevationDTO.getReservationId(),
                    resevationDTO.getCustomerId(),
                    resevationDTO.getDescription()
            );
            reservationTMS.add(reservationTM);
        }
        tblreservation.setItems(reservationTMS);
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {

        loadTableData();

        btnSave.setDisable(false);

        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        txtresid.setText("");
        txtcusid.setText("");
        txtdesc.setText("");


    }

    @FXML
    private Button AddSupplier;

    @FXML
    private Label CusID;

    @FXML
    private Label Description;

    @FXML
    private Label ReservationId;

    @FXML
    private AnchorPane ancSupplier;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<ReservationTM, Integer> colcustid;

    @FXML
    private TableColumn<ReservationTM, String> coldescription;

    @FXML
    private TableColumn<ReservationTM, Integer> colreservationId;

    @FXML
    private Button reportBtn;

    @FXML
    private Button supplier;

    @FXML
    private TableView<?> tblSupplier;

    @FXML
    private TextField txtcusid;

    @FXML
    private TextField txtdesc;

    @FXML
    private TextField txtresid;

    @FXML
    private VBox vbox1;

    @FXML
    void OnActionSupplier(ActionEvent event) {

    }

    @FXML
    void deleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        String branchID = txtresid.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = reservationBO.Delete(Integer.parseInt(branchID));
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Reservation  deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Reservation...!").show();
            }
        }

    }

    @FXML
    void onClickedTable(MouseEvent event) {

        ReservationTM reservationTM = (ReservationTM) tblreservation.getSelectionModel().getSelectedItem();
        if (reservationTM != null){
            txtresid.setText(String.valueOf(reservationTM.getReservationId()));
            txtcusid.setText(String.valueOf(reservationTM.getCustomerId()));
            txtdesc.setText(reservationTM.getDescription());



            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }


    }

    @FXML
    void reportOnAction(ActionEvent event) {

    }

    @FXML
    void saveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {


        int reservationId = Integer.parseInt(txtresid.getText());
        int CustomerId = Integer.parseInt(txtcusid.getText());
        String Description = txtdesc.getText();


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
        ResevationDTO resevationDTO = new ResevationDTO(
                reservationId,
                CustomerId,
                Description

        );

        boolean isSaved = reservationBO.Save(resevationDTO);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Reservation saved...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to save Reservation...!").show();
        }
        //   }

    }

    @FXML
    void updateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {


        int reservationId = Integer.parseInt(txtresid.getText());
        int CustomerId = Integer.parseInt(txtcusid.getText());
        String Description = txtdesc.getText();




        ResevationDTO resevationDTO = new ResevationDTO(
                reservationId,
                CustomerId,
                Description

        );

        boolean isUpdate = reservationBO.update(resevationDTO);
        if (isUpdate) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Reservation update...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to update Reservation...!").show();
        }
    }



}
