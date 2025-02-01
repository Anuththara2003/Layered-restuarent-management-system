package com.assignment.resturentmanagementsystem.dao.custom;

import com.assignment.resturentmanagementsystem.Dto.OrderDTo;
import com.assignment.resturentmanagementsystem.dao.CrudDao;
import com.assignment.resturentmanagementsystem.entity.OrderEntity;
import com.mysql.cj.x.protobuf.MysqlxCrud;

public interface OrderDao extends CrudDao<OrderEntity> {
}
