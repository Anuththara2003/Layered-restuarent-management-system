package com.assignment.resturentmanagementsystem.bo.custom.impl;

import com.assignment.resturentmanagementsystem.Dto.OrderViewDTO;
import com.assignment.resturentmanagementsystem.bo.custom.OrderViewBo;
import com.assignment.resturentmanagementsystem.dao.CrudUtil;
import com.assignment.resturentmanagementsystem.dao.DaoFactory;
import com.assignment.resturentmanagementsystem.dao.custom.OrderViewDao;
import com.assignment.resturentmanagementsystem.entity.OrderViewEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderViewBoimpl implements OrderViewBo {
    OrderViewDao orderViewDao = (OrderViewDao) DaoFactory.getInstance().getDao(DaoFactory.ServiceType.ORDERVIEW);
    @Override
    public boolean Save(OrderViewDTO Dto) throws SQLException, ClassNotFoundException {
        return orderViewDao.Save(new OrderViewEntity(Dto.getOrderId(),
                Dto.getCustomerID(),
                Dto.getItemId(),
                Dto.getItemName(),
                Dto.getUnitPrice(),
                Dto.getCartQuantity(),
                Dto.getTotal(),
                Dto.getType(),
                Dto.getFeeId(),
                Dto.getRemoveBtn())
        );
    }

    public int getNextOrderId() throws SQLException, ClassNotFoundException {
        return orderViewDao.getNextOrderId();
    }
}
