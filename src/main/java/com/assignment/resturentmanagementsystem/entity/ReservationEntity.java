package com.assignment.resturentmanagementsystem.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReservationEntity {
    private int reservationId;
    private int CustomerId;
    private String Description;
}
