package com.assignment.resturentmanagementsystem.bo.custom.impl;

import com.assignment.resturentmanagementsystem.DBConnection.DBConnection;
import com.assignment.resturentmanagementsystem.Dto.OrderDTo;
import com.assignment.resturentmanagementsystem.bo.custom.OrderBo;
import com.assignment.resturentmanagementsystem.dao.DaoFactory;
import com.assignment.resturentmanagementsystem.dao.custom.MenuDao;
import com.assignment.resturentmanagementsystem.dao.custom.OrderDao;
import com.assignment.resturentmanagementsystem.dao.custom.impl.OrderDaoImpl;
import com.assignment.resturentmanagementsystem.entity.OrderEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBoImpl implements OrderBo {

    OrderDao orderDao = (OrderDao) DaoFactory.getInstance().getDao(DaoFactory.ServiceType.ORDER);
    MenuDao menuDao = (MenuDao) DaoFactory.getInstance().getDao(DaoFactory.ServiceType.MENU);

    @Override
    public ArrayList<OrderDTo> getAll() throws SQLException, ClassNotFoundException {
       ArrayList<OrderEntity> orderEntities = orderDao.getAll();
       ArrayList<OrderDTo> orderDTos = new ArrayList<>();

       for (OrderEntity orderEntity : orderEntities){
           orderDTos.add(new OrderDTo(orderEntity.getOrderId(),
                   orderEntity.getType(),
                   orderEntity.getFeedbackID(),
                   orderEntity.getCustomerID(),
                   orderEntity.getOrderId(),
                   orderEntity.getQuantity(),
                   orderEntity.getItemId()));
       }
       return orderDTos;

    }

    @Override
    public boolean Save(OrderDTo orderDTo) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);

//            boolean isSaved = CrudUtil.execute("insert into orders values(?,?,?,?,?,?,?)",
//                    Dto.getOrderId(),
//                    Dto.getType(),
//                    Dto.getFeeId(),
//                    Dto.getCustomerID(),
//                    Dto.getUnitPrice(),
//                    Dto.getCartQuantity(),
//                    Dto.getItemId()
//            );

            boolean isSaved = orderDao.Save(new OrderEntity(orderDTo.getOrderId(),
                    orderDTo.getType(),
                    orderDTo.getFeedbackID(),
                    orderDTo.getCustomerID(),
                    orderDTo.getPrice(),
                    orderDTo.getQuantity(),
                    orderDTo.getItemId())
            );



            if (isSaved){
                System.out.println(isSaved);
                boolean isPaymentReduce = menuDao.reduceQentity(orderDTo.getItemId(),orderDTo.getQuantity());
                if (isPaymentReduce){
                    connection.commit();
                    return true;
                }

            }
            connection.rollback();
            return false;

        }catch (Exception e){
            e.printStackTrace();
            connection.rollback();
            return false;

        }finally {

            connection.setAutoCommit(true);
        }
    }

    @Override
    public boolean Delete(int Order_Id) throws SQLException, ClassNotFoundException {
        return orderDao.Delete(Order_Id);
    }
}
