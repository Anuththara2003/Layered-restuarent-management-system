package com.assignment.resturentmanagementsystem.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HomePageController {

    @FXML
    private AnchorPane anchome;

    @FXML
    private Button clickhear;

    @FXML
    void onclickhear(MouseEvent event) throws IOException {
           anchome.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml"));
            anchome.getChildren().add(load);
            load.prefWidthProperty().bind(anchome.widthProperty());
            load.prefHeightProperty().bind(anchome.heightProperty());

            AnchorPane.setTopAnchor(load, 0.0);
            AnchorPane.setRightAnchor(load, 0.0);
            AnchorPane.setBottomAnchor(load, 0.0);
            AnchorPane.setLeftAnchor(load, 0.0);
        }

    }


