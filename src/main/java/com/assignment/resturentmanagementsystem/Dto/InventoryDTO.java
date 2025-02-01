package com.assignment.resturentmanagementsystem.Dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InventoryDTO {
    private int InventoryId;
    private String Description;
    private int BranchId;
}
