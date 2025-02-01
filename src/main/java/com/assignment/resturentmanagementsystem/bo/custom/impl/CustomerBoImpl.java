package com.assignment.resturentmanagementsystem.bo.custom.impl;

import com.assignment.resturentmanagementsystem.Dto.CustomerDto;
import com.assignment.resturentmanagementsystem.bo.custom.CustomerBo;
import com.assignment.resturentmanagementsystem.dao.CrudUtil;
import com.assignment.resturentmanagementsystem.dao.DaoFactory;
import com.assignment.resturentmanagementsystem.dao.custom.CustomerDao;
import com.assignment.resturentmanagementsystem.dao.custom.impl.CustomerDaoImpl;
import com.assignment.resturentmanagementsystem.entity.CustomerEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBoImpl implements CustomerBo {
    CustomerDao customerDao = (CustomerDao) DaoFactory.getInstance().getDao(DaoFactory.ServiceType.CUSTOMER);

    @Override
    public ArrayList<CustomerDto> getAll() throws SQLException, ClassNotFoundException {
       ArrayList<CustomerEntity> customerEntities = customerDao.getAll();
       ArrayList<CustomerDto> customerDtos = new ArrayList<>();

       for (CustomerEntity customerEntity: customerEntities) {
           customerDtos.add(new CustomerDto(customerEntity.getCustomerId(),
                   customerEntity.getCustomerName(),
                   customerEntity.getCustomerAddress(),
                   customerEntity.getCustomerAge()
           ));
       }
       return customerDtos;
    }

    @Override
    public boolean Save(CustomerDto Dto) throws SQLException, ClassNotFoundException {
        return customerDao.Save(new CustomerEntity(Dto.getCustomerId(),
                Dto.getCustomerName(),
                Dto.getCustomerAddress(),
                Dto.getCustomerAge())
        );
    }

    @Override
    public boolean update(CustomerDto Dto) throws SQLException, ClassNotFoundException {
        return customerDao.update(new CustomerEntity(Dto.getCustomerId(),
                Dto.getCustomerName(),
                Dto.getCustomerAddress(),
                Dto.getCustomerAge())
        );
    }

    @Override
    public boolean Delete(int Id) throws SQLException, ClassNotFoundException {
        return customerDao.Delete(Id);
    }

    public ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException {
        return customerDao.getAllCustomerIds();

    }

    public CustomerDto findById(int selectedCusId) throws SQLException, ClassNotFoundException {
        return customerDao.findById(selectedCusId);
    }
}
