package com.assignment.resturentmanagementsystem.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderEntity {
    private int orderId;
    private String Type;
    private int FeedbackID;
    private int CustomerID;
    private double Price;
    private int Quantity;
    private int ItemId;
}
