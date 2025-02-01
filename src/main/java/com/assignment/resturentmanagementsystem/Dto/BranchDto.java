package com.assignment.resturentmanagementsystem.Dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BranchDto {
    private int branchId;
    private String branchName;
    private String branchAddress;
    private int employeeId;
}
