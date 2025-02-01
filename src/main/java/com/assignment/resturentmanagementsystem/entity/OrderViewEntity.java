package com.assignment.resturentmanagementsystem.entity;

import javafx.scene.control.Button;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderViewEntity {
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

}
