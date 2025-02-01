package com.assignment.resturentmanagementsystem.bo.custom.impl;

import com.assignment.resturentmanagementsystem.Dto.SupplierDTO;
import com.assignment.resturentmanagementsystem.bo.custom.SupplierBo;
import com.assignment.resturentmanagementsystem.dao.DaoFactory;
import com.assignment.resturentmanagementsystem.dao.custom.SuppilerDao;
import com.assignment.resturentmanagementsystem.entity.SupplierEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBoImpl implements SupplierBo {

    SuppilerDao suppilerDao = (SuppilerDao) DaoFactory.getInstance().getDao(DaoFactory.ServiceType.SUPPLIER);
    @Override
    public ArrayList<SupplierDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<SupplierEntity> supplierEntities = suppilerDao.getAll();
        ArrayList<SupplierDTO> supplierDTOS = new ArrayList<>();

        for(SupplierEntity supplierEntity : supplierEntities){
            supplierDTOS.add(new SupplierDTO(supplierEntity.getSupplierId(),
                    supplierEntity.getSupplierName())
            );
        }
        return supplierDTOS;
    }

    @Override
    public boolean Save(SupplierDTO DTO) throws SQLException, ClassNotFoundException {
      return suppilerDao.Save(new SupplierEntity(DTO.getSupplierId(),
              DTO.getSupplierName())
      );
    }

    @Override
    public boolean update(SupplierDTO DTO) throws SQLException, ClassNotFoundException {
        return suppilerDao.update(new SupplierEntity(DTO.getSupplierId(),
                DTO.getSupplierName())
        );
    }

    @Override
    public boolean Delete(int Id) throws SQLException, ClassNotFoundException {
        return suppilerDao.Delete(Id);
    }
}
