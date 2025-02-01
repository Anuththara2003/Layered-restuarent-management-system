package com.assignment.resturentmanagementsystem.Dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerDto {
 private int customerId;
 private String customerName;
 private String customerAddress;
private int customerAge;

}
