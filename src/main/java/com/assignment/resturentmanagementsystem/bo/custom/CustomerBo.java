package com.assignment.resturentmanagementsystem.bo.custom;

import com.assignment.resturentmanagementsystem.Dto.CustomerDto;
import com.assignment.resturentmanagementsystem.bo.SuperBo;
import com.assignment.resturentmanagementsystem.dao.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBo extends SuperBo {

    public ArrayList<CustomerDto> getAll() throws SQLException, ClassNotFoundException;
    public boolean Save(CustomerDto Dto) throws SQLException, ClassNotFoundException ;
    public boolean update(CustomerDto Dto) throws SQLException, ClassNotFoundException ;
    public boolean Delete(int Id) throws SQLException, ClassNotFoundException ;
    ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException;
    CustomerDto findById(int selectedCusId) throws SQLException, ClassNotFoundException;

}
