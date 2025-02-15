package com.assignment.resturentmanagementsystem.dao.custom.impl;

import com.assignment.resturentmanagementsystem.DBConnection.DBConnection;
import com.assignment.resturentmanagementsystem.Dto.OrderViewDTO;
import com.assignment.resturentmanagementsystem.dao.CrudUtil;
import com.assignment.resturentmanagementsystem.dao.custom.OrderViewDao;
import com.assignment.resturentmanagementsystem.entity.OrderViewEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderVIewDaoImpl implements OrderViewDao {

    @Override
    public ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<OrderViewEntity> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean Save(OrderViewEntity Dto) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute("insert into orders values(?,?,?,?,?,?,?)",
                Dto.getOrderId(),
                Dto.getType(),
                Dto.getFeeId(),
                Dto.getCustomerID(),
                Dto.getUnitPrice(),
                Dto.getCartQuantity(),
                Dto.getItemId()
        );
    }

    @Override
    public boolean update(OrderViewEntity Dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean Delete(int Id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public int getNextOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst =  CrudUtil.execute("select Order_Id from orders order by Order_Id desc limit 1");

        if (rst.next()){
            return rst.getInt(1);
        }
        return  0;
    }
}
