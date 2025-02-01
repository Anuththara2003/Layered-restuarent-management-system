package com.assignment.resturentmanagementsystem.bo.custom;

import com.assignment.resturentmanagementsystem.DBConnection.DBConnection;
import com.assignment.resturentmanagementsystem.Dto.OrderDTo;
import com.assignment.resturentmanagementsystem.bo.SuperBo;
import com.assignment.resturentmanagementsystem.dao.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderBo extends SuperBo {

     ArrayList<OrderDTo> getAll() throws SQLException, ClassNotFoundException ;
     boolean Save(OrderDTo DTo) throws SQLException, ClassNotFoundException ;
     boolean Delete(int Id ) throws SQLException, ClassNotFoundException ;

}
