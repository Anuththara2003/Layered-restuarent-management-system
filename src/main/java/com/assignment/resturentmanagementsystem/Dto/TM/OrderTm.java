package com.assignment.resturentmanagementsystem.Dto.TM;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class OrderTm {

        private int orderId;
        private String Type;
        private int FeedbackID;
        private int CustomerID;
        private int ItemId;
        private double Price;
        private int Quantity;

    }
