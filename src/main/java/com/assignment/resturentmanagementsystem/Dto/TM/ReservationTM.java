package com.assignment.resturentmanagementsystem.Dto.TM;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReservationTM {

    private int reservationId;
    private int CustomerId;
    private String Description;


}
