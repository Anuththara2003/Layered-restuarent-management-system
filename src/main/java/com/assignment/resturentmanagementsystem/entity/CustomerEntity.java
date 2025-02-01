package com.assignment.resturentmanagementsystem.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerEntity {
    private int customerId;
    private String customerName;
    private String customerAddress;
    private int customerAge;

}
