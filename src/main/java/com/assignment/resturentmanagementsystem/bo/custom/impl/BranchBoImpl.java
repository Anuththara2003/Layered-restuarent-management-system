package com.assignment.resturentmanagementsystem.bo.custom.impl;

import com.assignment.resturentmanagementsystem.Dto.BranchDto;
import com.assignment.resturentmanagementsystem.bo.custom.BranchBo;
import com.assignment.resturentmanagementsystem.dao.DaoFactory;
import com.assignment.resturentmanagementsystem.dao.custom.BranchDao;
import com.assignment.resturentmanagementsystem.entity.BranchEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class BranchBoImpl implements BranchBo {

    BranchDao branchDao = (BranchDao) DaoFactory.getInstance().getDao(DaoFactory.ServiceType.BRANCH);
    @Override
    public ArrayList<BranchDto> getAll() throws SQLException, ClassNotFoundException {
       ArrayList<BranchEntity> branchEntities = branchDao.getAll();
       ArrayList<BranchDto> branchDtos = new ArrayList<>();

       for (BranchEntity branchEntity : branchEntities){
           branchDtos.add(new BranchDto(branchEntity.getBranchId(),
                   branchEntity.getBranchName(),
                   branchEntity.getBranchAddress(),
                   branchEntity.getEmployeeId()));
       }
       return branchDtos;

    }

    @Override
    public boolean Save(BranchDto Dto) throws SQLException, ClassNotFoundException {
        return branchDao.Save(new BranchEntity(Dto.getBranchId(),
                Dto.getBranchName(),
                Dto.getBranchAddress(),
                Dto.getEmployeeId()));
    }

    @Override
    public boolean update(BranchDto Dto) throws SQLException, ClassNotFoundException {
       return branchDao.update(new BranchEntity(Dto.getBranchId(),
               Dto.getBranchName(),
               Dto.getBranchAddress(),
               Dto.getEmployeeId()));
    }

    @Override
    public boolean Delete(int Id) throws SQLException, ClassNotFoundException {
       return branchDao.Delete(Id);
    }
}
