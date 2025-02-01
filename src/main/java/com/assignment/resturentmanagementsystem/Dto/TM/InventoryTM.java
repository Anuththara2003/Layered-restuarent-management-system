package com.assignment.resturentmanagementsystem.Dto.TM;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InventoryTM {
    private int InventoryId;
    private String Description;
    private int BranchId;
}
