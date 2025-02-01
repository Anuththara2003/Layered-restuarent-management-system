package com.assignment.resturentmanagementsystem.dao.custom.impl;

import com.assignment.resturentmanagementsystem.Dto.CustomerDto;
import com.assignment.resturentmanagementsystem.dao.CrudDao;
import com.assignment.resturentmanagementsystem.dao.CrudUtil;
import com.assignment.resturentmanagementsystem.dao.custom.CustomerDao;
import com.assignment.resturentmanagementsystem.entity.CustomerEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDaoImpl implements CustomerDao {

    public ArrayList<CustomerEntity> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select * from customer");

        ArrayList<CustomerEntity> customerEntities = new ArrayList<>();

        while (rst.next()) {
            CustomerEntity customerEntity = new CustomerEntity(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4)


            );
            customerEntities.add(customerEntity);
        }
        return customerEntities;
    }

    @Override
    public boolean Save(CustomerEntity Dto) throws SQLException, ClassNotFoundException {
        boolean isSaved = CrudUtil.execute(
                "insert into customer values (?,?,?,?)",
                Dto.getCustomerId(),
                Dto.getCustomerName(),
                Dto.getCustomerAddress(),
                Dto.getCustomerAge()

        );
        return isSaved;
    }


    @Override
    public boolean update(CustomerEntity Dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "update customer set Name=?, Address=?, Age=? where Cust_Id=?",

                Dto.getCustomerName(),
                Dto.getCustomerAddress(),
                Dto.getCustomerAge(),
                Dto.getCustomerId()

        );
    }


    @Override
    public boolean Delete(int Id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from customer where Cust_Id=?", Id);
    }


    @Override
    public CustomerDto findById(int selectedCusId) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select * from customer where Cust_Id=?", selectedCusId);

        if (rst.next()) {
            return new CustomerDto(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4)
            );
        }
        return null;
    }

    @Override
    public ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException {

        ResultSet rst = CrudUtil.execute("select Cust_Id from customer");

        ArrayList<String> customerIds = new ArrayList<>();

        while (rst.next()) {
            customerIds.add(rst.getString(1));
        }

        return customerIds;

    }

}

