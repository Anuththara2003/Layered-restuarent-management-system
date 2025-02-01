package com.assignment.resturentmanagementsystem.dao.custom;

import com.assignment.resturentmanagementsystem.DBConnection.DBConnection;
import com.assignment.resturentmanagementsystem.Dto.OrderDTo;
import com.assignment.resturentmanagementsystem.Dto.OrderViewDTO;
import com.assignment.resturentmanagementsystem.dao.CrudDao;
import com.assignment.resturentmanagementsystem.dao.CrudUtil;
import com.assignment.resturentmanagementsystem.dao.SuperDao;
import com.assignment.resturentmanagementsystem.entity.OrderViewEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderViewDao extends CrudDao<OrderViewEntity> {
     ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException;
     int getNextOrderId() throws SQLException, ClassNotFoundException;

}
