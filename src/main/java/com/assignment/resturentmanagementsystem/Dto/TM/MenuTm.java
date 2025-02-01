package com.assignment.resturentmanagementsystem.Dto.TM;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MenuTm {
    private int menuId;
    private String menuName;
    private int CratagoryId;
    private double price;
    private int Qty;

}
