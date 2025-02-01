package com.assignment.resturentmanagementsystem.bo.custom;

import com.assignment.resturentmanagementsystem.Dto.EmployeeDTO;
import com.assignment.resturentmanagementsystem.bo.SuperBo;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBo extends SuperBo {
     ArrayList<EmployeeDTO> getAll() throws SQLException, ClassNotFoundException ;
     boolean Save(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException ;
      boolean update(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException ;
      boolean Delete(int Emp_Id ) throws SQLException, ClassNotFoundException ;
}
