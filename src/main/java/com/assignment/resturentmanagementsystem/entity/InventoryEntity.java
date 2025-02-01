package com.assignment.resturentmanagementsystem.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InventoryEntity {
    private int InventoryId;
    private String Description;
    private int BranchId;
}
