package com.assignment.resturentmanagementsystem.Dto;


import com.assignment.resturentmanagementsystem.bo.BoFactory;
import com.assignment.resturentmanagementsystem.bo.custom.CustomerBo;
import com.assignment.resturentmanagementsystem.bo.custom.FeedbackBo;
import com.assignment.resturentmanagementsystem.bo.custom.MenuBo;
import javafx.scene.control.Button;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderViewDTO {

//    private double Total;
//    private String Type;
//    private int ItemId;
//    private double Price;
//    private String name;
//    private int Quantity;
//    private Button remove;
    private int orderId;
    private int CustomerID;
    private String itemId;
    private String itemName;
    private double unitPrice;
    private int cartQuantity;
    private double total;
    private String type;
    private String FeeId;
    private Button removeBtn;
//
//    CustomerBo customerBo = (CustomerBo) BoFactory.getInstance().getSuperService(BoFactory.ServiceType.CUSTOMER);
//    FeedbackBo feedbackBo = (FeedbackBo) BoFactory.getInstance().getSuperService(BoFactory.ServiceType.FEEDBACK);
//    MenuBo menuBo = (MenuBo) BoFactory.getInstance().getSuperService(BoFactory.ServiceType.MENU);

}
