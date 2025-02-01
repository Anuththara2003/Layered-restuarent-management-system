package com.assignment.resturentmanagementsystem.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FeedbackEntity {
    private int FeedbackID;
    private String Description;
    private int CustomerID;
}
