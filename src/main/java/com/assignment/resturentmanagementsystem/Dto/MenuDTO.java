package com.assignment.resturentmanagementsystem.Dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class MenuDTO {
    private int menuId;
    private String menuName;
    private int CratagoryId;
    private double price;
    private int Qty;

}
