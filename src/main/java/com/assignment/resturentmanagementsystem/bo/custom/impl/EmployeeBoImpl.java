package com.assignment.resturentmanagementsystem.bo.custom.impl;

import com.assignment.resturentmanagementsystem.Dto.EmployeeDTO;
import com.assignment.resturentmanagementsystem.bo.custom.EmployeeBo;
import com.assignment.resturentmanagementsystem.dao.CrudUtil;
import com.assignment.resturentmanagementsystem.dao.DaoFactory;
import com.assignment.resturentmanagementsystem.dao.custom.EmployeeDao;
import com.assignment.resturentmanagementsystem.entity.EmployeeEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBoImpl implements EmployeeBo {

    EmployeeDao employeeDao = (EmployeeDao) DaoFactory.getInstance().getDao(DaoFactory.ServiceType.EMPLOYEE);
    public ArrayList<EmployeeDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeEntity> employeeEntities = employeeDao.getAll();
        ArrayList<EmployeeDTO> employeeDTOS = new ArrayList<>();

        for (EmployeeEntity employeeEntity :employeeEntities){
            employeeDTOS.add(new EmployeeDTO(employeeEntity.getEmployeeId(),
                    employeeEntity.getEmpName(),
                    employeeEntity.getEmployeeAddress())
            );
        }
        return employeeDTOS;
    }


    public boolean Save(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return employeeDao.Save(new EmployeeEntity(employeeDTO.getEmployeeId(),
                employeeDTO.getEmpName(),
                employeeDTO.getEmployeeAddress())
        );
    }

    public  boolean update(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return employeeDao.update(new EmployeeEntity(employeeDTO.getEmployeeId(),
                employeeDTO.getEmpName(),
                employeeDTO.getEmployeeAddress())
        );
    }

    public  boolean Delete(int Emp_Id ) throws SQLException, ClassNotFoundException {
        return employeeDao.Delete(Emp_Id);
    }


}
