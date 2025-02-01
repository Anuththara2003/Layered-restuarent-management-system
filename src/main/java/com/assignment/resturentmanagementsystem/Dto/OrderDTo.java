package com.assignment.resturentmanagementsystem.Dto;


import lombok.*;

import java.sql.Date;
import java.util.ArrayList;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDTo {
    private int orderId;
    private String Type;
    private int FeedbackID;
    private int CustomerID;
    private double Price;
    private int Quantity;
    private int ItemId;



}
