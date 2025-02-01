module com.assignment.tictactoe.service.resturentmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;
    requires jdk.security.jgss;
    requires net.sf.jasperreports.core;
    requires com.google.protobuf;
    requires mysql.connector.j;


    opens com.assignment.resturentmanagementsystem.Controller to javafx.fxml;
    opens com.assignment.resturentmanagementsystem.Dto.TM to javafx.base;
    exports com.assignment.resturentmanagementsystem;
}