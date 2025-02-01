package com.assignment.resturentmanagementsystem.bo.custom.impl;

import com.assignment.resturentmanagementsystem.Dto.RoleDTO;
import com.assignment.resturentmanagementsystem.bo.custom.RoleBo;
import com.assignment.resturentmanagementsystem.dao.DaoFactory;
import com.assignment.resturentmanagementsystem.dao.custom.RoleDao;
import com.assignment.resturentmanagementsystem.entity.RoleEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class RoleBoImpl implements RoleBo {

    RoleDao roleDao = (RoleDao) DaoFactory.getInstance().getDao(DaoFactory.ServiceType.ROLE);
    @Override
    public ArrayList<RoleDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<RoleEntity> roleEntities = roleDao.getAll();
        ArrayList<RoleDTO> roleDTOS = new ArrayList<>();

        for(RoleEntity roleEntity : roleEntities){
            roleDTOS.add(new RoleDTO(roleEntity.getRoleId(),
                    roleEntity.getRoleName(),
                    roleEntity.getEmployeeId())
            );
        }
        return roleDTOS;
    }

    @Override
    public boolean Save(RoleDTO DTO) throws SQLException, ClassNotFoundException {
        return roleDao.Save(new RoleEntity(DTO.getRoleId(),
                DTO.getRoleName(),
                DTO.getEmployeeId())
        );
    }

    @Override
    public boolean update(RoleDTO DTO) throws SQLException, ClassNotFoundException {
        return roleDao.update(new RoleEntity(DTO.getRoleId(),
                DTO.getRoleName(),
                DTO.getEmployeeId())
        );
    }

    @Override
    public boolean Delete(int Id) throws SQLException, ClassNotFoundException {
        return roleDao.Delete(Id);
    }
}
