package com.assignment.resturentmanagementsystem.dao.custom;

import com.assignment.resturentmanagementsystem.Dto.CustomerDto;
import com.assignment.resturentmanagementsystem.dao.CrudDao;
import com.assignment.resturentmanagementsystem.dao.CrudUtil;
import com.assignment.resturentmanagementsystem.entity.CustomerEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDao extends CrudDao<CustomerEntity> {
    CustomerDto findById(int selectedCusId) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException;
}