package com.assignment.resturentmanagementsystem.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class MenuEntity {
    private int menuId;
    private String menuName;
    private int CratagoryId;
    private double price;
    private int Qty;
}
