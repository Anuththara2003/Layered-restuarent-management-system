package com.assignment.resturentmanagementsystem.Dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FeedbackDTO {
    private int FeedbackID;
    private String Description;
    private int CustomerID;

}
