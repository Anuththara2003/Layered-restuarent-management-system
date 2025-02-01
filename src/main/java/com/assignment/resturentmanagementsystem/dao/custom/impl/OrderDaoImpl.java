package com.assignment.resturentmanagementsystem.dao.custom.impl;

import com.assignment.resturentmanagementsystem.Dto.OrderDTo;
import com.assignment.resturentmanagementsystem.dao.CrudUtil;
import com.assignment.resturentmanagementsystem.dao.custom.OrderDao;
import com.assignment.resturentmanagementsystem.entity.OrderEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDaoImpl implements OrderDao {
    @Override
    public ArrayList<OrderEntity> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select * from orders");

        ArrayList<OrderEntity> orderEntities = new ArrayList<>();

        while (rst.next()) {
            OrderEntity orderEntity = new OrderEntity(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getInt(4),
                    rst.getDouble(5),
                    rst.getInt(7),
                    rst.getInt(6)
            );

            orderEntities.add(orderEntity);
            System.out.println(orderEntity);
        }
        return orderEntities;
    }

    @Override
    public boolean Save(OrderEntity Dto) throws SQLException, ClassNotFoundException {
        boolean isSaved =  CrudUtil.execute(
                "insert into orders values (?,?,?,?,?,?,?)",

                Dto.getOrderId(),
                Dto.getType(),
                Dto.getFeedbackID(),
                Dto.getCustomerID(),
                Dto.getPrice(),
                Dto.getQuantity(),
                Dto.getItemId()

        );

        return isSaved;
    }

    @Override
    public boolean update(OrderEntity Dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean Delete(int Id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM orders WHERE Order_Id = ?",Id);
    }
}
