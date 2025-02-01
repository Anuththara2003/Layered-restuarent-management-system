package com.assignment.resturentmanagementsystem.Dto.TM;

import javafx.scene.control.Button;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartTM {


    private int itemId;
    private String itemName;
    private double unitPrice;
    private int cartQuantity;
    private double total;
    private String FeedbackID;
    private String type;
    private Button removeBtn;

}
