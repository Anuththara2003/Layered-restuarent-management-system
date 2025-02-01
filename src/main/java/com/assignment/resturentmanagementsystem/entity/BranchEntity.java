package com.assignment.resturentmanagementsystem.entity;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BranchEntity {
    private int branchId;
    private String branchName;
    private String branchAddress;
    private int employeeId;
}
