package com.assignment.resturentmanagementsystem.Dto.TM;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BranchTM {
 private int branchId;
 private String branchName;
 private String branchAddress;
 private int employeeId;
}
