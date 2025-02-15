package com.assignment.resturentmanagementsystem.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DashboardController {

    @FXML
    private AnchorPane DashBoardAnc;

    @FXML
    private AnchorPane LoadPain;

    @FXML
    private Button LogOutBtn;

    @FXML
    void LogoutAction(ActionEvent event) throws IOException {
        DashBoardAnc.getChildren().clear();
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
        DashBoardAnc.getChildren().add(anchorPane);

    }

    @FXML
    void branchOnAction(ActionEvent event) throws IOException {
        LoadPain.getChildren().clear();
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/View/Branch.fxml"));
        LoadPain.getChildren().add(anchorPane);
    }

    @FXML
    void customerOnAction(ActionEvent event) throws IOException {
        LoadPain.getChildren().clear();
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/View/Customer.fxml"));
        LoadPain.getChildren().add(anchorPane);
    }
    @FXML
    void orderOnAction(ActionEvent event) throws IOException {
        LoadPain.getChildren().clear();
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/View/Order.fxml"));
        LoadPain.getChildren().add(anchorPane);

    }


    @FXML
    void MenuOnAction(ActionEvent event) throws IOException {

        LoadPain.getChildren().clear();
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/View/Menu.fxml"));
        LoadPain.getChildren().add(anchorPane);
    }

    @FXML
    void FeedbackOnAction(ActionEvent event) throws IOException {

        LoadPain.getChildren().clear();
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/View/Feedback.fxml"));
        LoadPain.getChildren().add(anchorPane);

    }


    @FXML
    void EmployeeOnAction(ActionEvent event) throws IOException {
        LoadPain.getChildren().clear();
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/View/Employee.fxml"));
        LoadPain.getChildren().add(anchorPane);


    }
    @FXML
    void SupplierOnAction(ActionEvent event) throws IOException {
        LoadPain.getChildren().clear();
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/View/Supplier.fxml"));
        LoadPain.getChildren().add(anchorPane);


    }


    public void EmailOnAction(ActionEvent actionEvent) throws IOException {
        LoadPain.getChildren().clear();
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/View/SendMailPage.fxml"));
        LoadPain.getChildren().add(anchorPane);

    }
}
