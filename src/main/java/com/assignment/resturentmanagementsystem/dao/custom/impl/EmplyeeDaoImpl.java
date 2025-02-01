package com.assignment.resturentmanagementsystem.dao.custom.impl;

import com.assignment.resturentmanagementsystem.Dto.EmployeeDTO;
import com.assignment.resturentmanagementsystem.bo.BoFactory;
import com.assignment.resturentmanagementsystem.bo.custom.EmployeeBo;
import com.assignment.resturentmanagementsystem.dao.CrudUtil;
import com.assignment.resturentmanagementsystem.dao.DaoFactory;
import com.assignment.resturentmanagementsystem.dao.custom.EmployeeDao;
import com.assignment.resturentmanagementsystem.entity.EmployeeEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmplyeeDaoImpl implements EmployeeDao {

    public ArrayList<EmployeeEntity> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select * from employee");

        ArrayList<EmployeeEntity> employeeEntities = new ArrayList<>();

        while (rst.next()) {
            EmployeeEntity employeeEntity = new EmployeeEntity(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3)


            );
            employeeEntities.add(employeeEntity);
        }
        return employeeEntities;
    }


    public boolean Save(EmployeeEntity employeeDTO) throws SQLException, ClassNotFoundException {

        boolean isSaved = CrudUtil.execute(
                "insert into employee values (?,?,?)",

                employeeDTO.getEmployeeId(),
                employeeDTO.getEmpName(),
                employeeDTO.getEmployeeAddress()


        );

        return isSaved;
    }

    public  boolean update(EmployeeEntity employeeDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "update employee set  Name=?, Address=? where Emp_Id=?",


                employeeDTO.getEmpName(),
                employeeDTO.getEmployeeAddress(),
                employeeDTO.getEmployeeId()

        );
    }

    public  boolean Delete(int Emp_Id ) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute("delete from employee where Emp_Id=?", Emp_Id);
    }


    public ArrayList<String> getAllEmployeeIDS() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select Emp_Id from employee");

        ArrayList<String> employeeids = new ArrayList<>();

        while (rst.next()) {
            employeeids.add(rst.getString(1));
        }

        return employeeids;
    }
}
