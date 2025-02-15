package com.assignment.resturentmanagementsystem.Controller;

import com.assignment.resturentmanagementsystem.bo.BoFactory;
import com.assignment.resturentmanagementsystem.bo.custom.MenuBo;
import com.assignment.resturentmanagementsystem.DBConnection.DBConnection;
import com.assignment.resturentmanagementsystem.Dto.MenuDTO;
import com.assignment.resturentmanagementsystem.Dto.TM.MenuTm;
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

public class MenuController  implements Initializable {

    public Button Category;
    public TextField txtmenu;
    public TextField txtname;
    public TextField txtcatagory;
    public TableView tblmenu;
    public Label UnitPrice;
    public TextField txtunitprice;
    public TextField txtqty;
    public Label Qty;


    MenuBo menuBo = (MenuBo) BoFactory.getInstance().getSuperService(BoFactory.ServiceType.MENU);


    public void initialize(URL url, ResourceBundle resourceBundle) {
        colMenuItemID.setCellValueFactory(new PropertyValueFactory<>("menuId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("menuName"));
        colCatagoryID.setCellValueFactory(new PropertyValueFactory<>("CratagoryId"));
        colUnitprice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colqty.setCellValueFactory(new PropertyValueFactory<>("Qty"));



        try {
            refreshPage();
            loadTableData();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load Menu id").show();
        }

    }



    private void loadTableData() throws SQLException, ClassNotFoundException {


        ArrayList<MenuDTO> menuDTOS = menuBo.getAll();

        ObservableList<MenuTm> menuTms = FXCollections.observableArrayList();

        for (MenuDTO menuDTO : menuDTOS) {
            MenuTm menuTm = new MenuTm(
                    menuDTO.getMenuId(),
                    menuDTO.getMenuName(),
                    menuDTO.getCratagoryId(),
                    menuDTO.getPrice(),
                    menuDTO.getQty()
            );
            menuTms.add(menuTm);
        }
        tblmenu.setItems(menuTms);


    }

    private void refreshPage() throws SQLException, ClassNotFoundException {

        // loadNextCustomerId();
        loadTableData();

        btnSave.setDisable(false);

        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        txtcatagory.setText("");
        txtname.setText("");
        txtcatagory.setText("");


    }


    @FXML
    private Label CatagoryID;

    @FXML
    private Button Catagoryn;

    @FXML
    private AnchorPane Menu;

    @FXML
    private Button MenuItem;

    @FXML
    private Label MenuItemID;

    @FXML
    private TextField MenuItemid;

    @FXML
    private Label Name;

    @FXML
    private TextField atagory;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<MenuDTO, Integer> colCatagoryID;

    @FXML
    private TableColumn<MenuDTO, Integer> colMenuItemID;

    @FXML
    private TableColumn<MenuDTO, String> colName;

    @FXML
    private TableColumn<MenuDTO, Double> colUnitprice;


    @FXML
    private TableColumn<MenuDTO, Integer> colqty;


    @FXML
    private TextField name;

    @FXML
    private Button reportBtn;

    @FXML
    private TableView<?> tblBranch;

    @FXML
    private VBox vbox1;

    @FXML
    void CatagoryonOnAction(ActionEvent event) throws IOException {

        Menu.getChildren().clear();
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/View/Ctagory.fxml"));
        Menu.getChildren().add(anchorPane);

    }

    @FXML
    void deleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {


    }

    @FXML
    void onClickedCatagory(MouseEvent event) {

    }

    @FXML
    void onClickedMenuItem(MouseEvent event) {

    }

    @FXML
    void onClickedTable(MouseEvent event) {

        MenuTm menuTm = (MenuTm) tblmenu.getSelectionModel().getSelectedItem();
        if (menuTm != null){
            txtmenu.setText(String.valueOf(menuTm.getMenuId()));
            txtname.setText(menuTm.getMenuName());
            txtcatagory.setText(String.valueOf(menuTm.getCratagoryId()));
            txtunitprice.setText(String.valueOf(menuTm.getPrice()));
            txtqty.setText(String.valueOf(menuTm.getQty()));


            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }


    }

    @FXML
    void onDelete(MouseEvent event) throws SQLException, ClassNotFoundException {

       // System.out.println("haiyooooo");
        String MenuItemID = txtmenu.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = menuBo.Delete(Integer.parseInt(MenuItemID));

            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Menu  deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete menu...!").show();
            }
        }

    }



        @FXML
    void onUpdate(MouseEvent event) {

    }

    @FXML
    void onclickedSave(MouseEvent event) {

    }

    @FXML
    void reportOnAction(ActionEvent event) {

        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass().getResourceAsStream("/Report/MenuDetails.jrxml")
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



        int menuId = Integer.parseInt(txtmenu.getText());
        String menuName = txtname.getText();
        int CratagoryId = Integer.parseInt(txtcatagory.getText());
        double price = Double.parseDouble(txtunitprice.getText());
        int Qty = Integer.parseInt(txtqty.getText());

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
        MenuDTO menuDTO = new MenuDTO(
                menuId,
                menuName,
                CratagoryId,
                price,
                Qty

        );

        boolean isSaved = menuBo.Save(menuDTO);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Menu saved...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to save Menu...!").show();
        }
        //   }

    }



    public void onOactionMenu(ActionEvent actionEvent) throws IOException {



    }

    public void updateAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


        System.out.println("heloooooo");

        int menuId = Integer.parseInt(txtmenu.getText());
        String menuName = txtname.getText();
        int CratagoryId = Integer.parseInt(txtcatagory.getText());
        double price = Double.parseDouble(txtunitprice.getText());
        int Qty = Integer.parseInt(txtqty.getText());


        MenuDTO menuDTO = new MenuDTO(
                menuId,
                menuName,
                CratagoryId,
                price,
                Qty

        );

        boolean isUpdate = menuBo.update(menuDTO);
        if (isUpdate) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Menu update...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to update Menu...!").show();
        }
    }

}
