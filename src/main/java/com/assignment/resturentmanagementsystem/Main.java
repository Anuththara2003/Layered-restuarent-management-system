package com.assignment.resturentmanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/Login.fxml" + ""))));

        stage.setTitle("RESTURENT MANAGEMENT SYSTEM");
        Image image = new Image(getClass().getResourceAsStream("/Images/res icon.jpg"));
        stage.getIcons().add(image);
        stage.setResizable(false);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
