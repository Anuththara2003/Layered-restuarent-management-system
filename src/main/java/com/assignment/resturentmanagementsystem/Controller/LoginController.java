package com.assignment.resturentmanagementsystem.Controller;

import com.assignment.resturentmanagementsystem.Dto.LoginDto;
import com.assignment.resturentmanagementsystem.bo.BoFactory;
import com.assignment.resturentmanagementsystem.bo.custom.LoginBo;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;


public class LoginController {
    @FXML
    private Button LogInButton;

    @FXML
    private PasswordField PswFieldId;

    @FXML
    private Hyperlink SignUpHiperLink;

    @FXML
    private TextField UserNameTxtId;

    @FXML
    private AnchorPane ancLogin;

    private final LoginBo loginService = (LoginBo) BoFactory.getInstance().getSuperService(BoFactory.ServiceType.LOGIN);


    public void LoginAction(ActionEvent actionEvent) throws IOException, ClassNotFoundException, SQLException {

        LoginDto loginDto = new LoginDto(
                UserNameTxtId.getText(),
                PswFieldId.getText());

        if (loginService.authenticate(loginDto)) {
            System.out.println("Login successful!");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/HomePage.fxml"));
            AnchorPane newPane = loader.load();

            AnchorPane.setTopAnchor(newPane, 0.0);
            AnchorPane.setRightAnchor(newPane, 0.0);
            AnchorPane.setBottomAnchor(newPane, 0.0);
            AnchorPane.setLeftAnchor(newPane, 0.0);

            ancLogin.getChildren().setAll(newPane);


        }

        else {

            if(loginService.authenticateUsername(UserNameTxtId.getText())) {
                UserNameTxtId.setStyle("-fx-text-box-border: green; -fx-text-inner-color: green;");
                PswFieldId.setStyle("-fx-text-box-border: red; -fx-text-inner-color: red;");
            }

            else{

                PswFieldId.setStyle("-fx-text-box-border: red; -fx-text-inner-color: red;");
                UserNameTxtId.setStyle("-fx-text-box-border: red; -fx-text-inner-color: red;");
            }


        }
    }

    public void SignUpMouseClickAction(MouseEvent mouseEvent) throws IOException {
        ancLogin.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/SignUP.fxml"));
        ancLogin.getChildren().add(load);
        load.prefWidthProperty().bind(ancLogin.widthProperty());
        load.prefHeightProperty().bind(ancLogin.heightProperty());

        AnchorPane.setTopAnchor(load, 0.0);
        AnchorPane.setRightAnchor(load, 0.0);
        AnchorPane.setBottomAnchor(load, 0.0);
        AnchorPane.setLeftAnchor(load, 0.0);


    }
    public void signuppageAction(MouseEvent mouseEvent) throws IOException {


        ancLogin.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/View/SignUp.fxml"));
        ancLogin.getChildren().add(load);

        load.prefWidthProperty().bind(ancLogin.widthProperty());
        load.prefHeightProperty().bind(ancLogin.heightProperty());

        AnchorPane.setTopAnchor(load, 0.0);
        AnchorPane.setRightAnchor(load, 0.0);
        AnchorPane.setBottomAnchor(load, 0.0);
        AnchorPane.setLeftAnchor(load, 0.0);
    }

    public void loginAction(MouseEvent mouseEvent) throws IOException, SQLException, ClassNotFoundException {

        System.out.println("Login Clicked");

        LoginDto loginDto = new LoginDto(
                UserNameTxtId.getText(),
                PswFieldId.getText());

        if (loginService.authenticate(loginDto)) {
            System.out.println("Login successful!");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Home.fxml"));
            AnchorPane newPane = loader.load();

            AnchorPane.setTopAnchor(newPane, 0.0);
            AnchorPane.setRightAnchor(newPane, 0.0);
            AnchorPane.setBottomAnchor(newPane, 0.0);
            AnchorPane.setLeftAnchor(newPane, 0.0);

            ancLogin.getChildren().setAll(newPane);


        }
    }
}


