package com.assignment.resturentmanagementsystem.Dto.TM;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FeedbackTM {
    private int FeedbackID;
    private String Description;
    private int CustomerID;

}
