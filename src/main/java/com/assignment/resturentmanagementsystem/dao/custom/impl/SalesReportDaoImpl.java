package com.assignment.resturentmanagementsystem.dao.custom.impl;

import com.assignment.resturentmanagementsystem.Dto.SalesReportDTO;
import com.assignment.resturentmanagementsystem.dao.CrudUtil;
import com.assignment.resturentmanagementsystem.dao.custom.SalesreportDao;
import com.assignment.resturentmanagementsystem.entity.SalesReportEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalesReportDaoImpl implements SalesreportDao {
    @Override
    public ArrayList<SalesReportEntity> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select * from salesreport");

        ArrayList<SalesReportEntity> salesReportEntities = new ArrayList<>();

        while (rst.next()) {
            SalesReportEntity salesReportEntity = new SalesReportEntity(
                    rst.getInt(1),
                    rst.getInt(2)



            );
            salesReportEntities.add(salesReportEntity);
        }
        return salesReportEntities;
    }

    @Override
    public boolean Save(SalesReportEntity Dto) throws SQLException, ClassNotFoundException {
        boolean isSaved =  CrudUtil.execute(
                "insert into salesreport values (?,?)",

                Dto.getSaleId(),
                Dto.getBranchId()

        );

        return isSaved;
    }

    @Override
    public boolean update(SalesReportEntity Dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "update salesreport set Branch_Id=?  where Sale_Id=?",


                Dto.getBranchId(),
                Dto.getSaleId()


        );
    }

    @Override
    public boolean Delete(int Id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from salesreport where Sale_Id=?", Id);
    }
}
