package com.assignment.resturentmanagementsystem.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class SignUpController {

    @FXML
    private Hyperlink LoginHyperLink;

    @FXML
    private Button SignUpButton;

    @FXML
    private AnchorPane ancSignUp;

    @FXML
    private Label email;

    @FXML
    private PasswordField emailTxt;

    @FXML
    private ImageView image1;

    @FXML
    private Label password;

    @FXML
    private PasswordField pwrdTxt;

    @FXML
    private Text signup;

    @FXML
    private Label text1;

    @FXML
    private Label username;

    @FXML
    private TextField usernameTxt;




    @FXML
    void LoginMouseClickAction(MouseEvent event) throws IOException {

            ancSignUp.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
            ancSignUp.getChildren().add(load);

            load.prefWidthProperty().bind(ancSignUp.widthProperty());
            load.prefHeightProperty().bind(ancSignUp.heightProperty());


            AnchorPane.setTopAnchor(load, 0.0);
            AnchorPane.setRightAnchor(load, 0.0);
            AnchorPane.setBottomAnchor(load, 0.0);
            AnchorPane.setLeftAnchor(load, 0.0);
        }


    @FXML
    void SignUpOnAction(ActionEvent event) {

    }

    @FXML
    void onsignUp(MouseEvent event) throws IOException {

            ancSignUp.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml"));
            ancSignUp.getChildren().add(load);
            load.prefWidthProperty().bind(ancSignUp.widthProperty());
            load.prefHeightProperty().bind(ancSignUp.heightProperty());

            AnchorPane.setTopAnchor(load, 0.0);
            AnchorPane.setRightAnchor(load, 0.0);
            AnchorPane.setBottomAnchor(load, 0.0);
            AnchorPane.setLeftAnchor(load, 0.0);
        }

    }


