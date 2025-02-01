package com.assignment.resturentmanagementsystem.bo.custom.impl;

import com.assignment.resturentmanagementsystem.Dto.SalesReportDTO;
import com.assignment.resturentmanagementsystem.bo.custom.SalesReportBo;
import com.assignment.resturentmanagementsystem.dao.DaoFactory;
import com.assignment.resturentmanagementsystem.dao.custom.SalesreportDao;
import com.assignment.resturentmanagementsystem.entity.SalesReportEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class SalesReportBoImpl implements SalesReportBo {
    SalesreportDao salesreportDao = (SalesreportDao) DaoFactory.getInstance().getDao(DaoFactory.ServiceType.SALESREPORT);
    @Override
    public ArrayList<SalesReportDTO> getAll() throws SQLException, ClassNotFoundException {
       ArrayList<SalesReportEntity> salesReportEntities = salesreportDao.getAll();
       ArrayList<SalesReportDTO> salesReportDTOS = new ArrayList<>();

       for(SalesReportEntity salesReportEntity : salesReportEntities){
           salesReportDTOS.add(new SalesReportDTO(salesReportEntity.getSaleId(),
                   salesReportEntity.getBranchId())
           );
       }
       return salesReportDTOS;
    }

    @Override
    public boolean Save(SalesReportDTO DTO) throws SQLException, ClassNotFoundException {
      return salesreportDao.Save(new SalesReportEntity(DTO.getSaleId(),
              DTO.getBranchId())
      );
    }

    @Override
    public boolean update(SalesReportDTO DTO) throws SQLException, ClassNotFoundException {
        return salesreportDao.update(new SalesReportEntity(DTO.getSaleId(),
                DTO.getBranchId())
        );
    }

    @Override
    public boolean Delete(int Id) throws SQLException, ClassNotFoundException {
      return salesreportDao.Delete(Id);
    }
}
