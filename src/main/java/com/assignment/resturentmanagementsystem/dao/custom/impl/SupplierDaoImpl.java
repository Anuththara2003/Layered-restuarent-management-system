package com.assignment.resturentmanagementsystem.dao.custom.impl;

import com.assignment.resturentmanagementsystem.Dto.SalesReportDTO;
import com.assignment.resturentmanagementsystem.Dto.SupplierDTO;
import com.assignment.resturentmanagementsystem.dao.CrudUtil;
import com.assignment.resturentmanagementsystem.dao.custom.SalesreportDao;
import com.assignment.resturentmanagementsystem.dao.custom.SuppilerDao;
import com.assignment.resturentmanagementsystem.entity.SupplierEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDaoImpl implements SuppilerDao {
    @Override
    public ArrayList<SupplierEntity> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select * from supplier");

        ArrayList<SupplierEntity> supplierEntities = new ArrayList<>();

        while (rst.next()) {
            SupplierEntity supplierEntity = new SupplierEntity(
                    rst.getInt(1),
                    rst.getString(2)


            );
            supplierEntities.add(supplierEntity);
        }
        return supplierEntities;
    }

    @Override
    public boolean Save(SupplierEntity Dto) throws SQLException, ClassNotFoundException {
        boolean isSaved =  CrudUtil.execute(
                "insert into supplier values (?,?)",

                Dto.getSupplierId(),
                Dto.getSupplierName()

        );
        return isSaved;
    }

    @Override
    public boolean update(SupplierEntity Dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "update supplier set Name=? where Sup_Id=?",

                Dto.getSupplierName(),
                Dto.getSupplierId()


        );
    }


    @Override
    public boolean Delete(int Id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from supplier where Sup_Id=?", Id);
    }
}
