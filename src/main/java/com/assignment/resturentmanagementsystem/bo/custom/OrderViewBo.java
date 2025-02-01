package com.assignment.resturentmanagementsystem.bo.custom;

import com.assignment.resturentmanagementsystem.Dto.OrderViewDTO;
import com.assignment.resturentmanagementsystem.bo.SuperBo;

import java.sql.SQLException;

public interface OrderViewBo extends SuperBo {
    boolean Save(OrderViewDTO Dto) throws SQLException, ClassNotFoundException;
    int getNextOrderId() throws SQLException, ClassNotFoundException;
}
