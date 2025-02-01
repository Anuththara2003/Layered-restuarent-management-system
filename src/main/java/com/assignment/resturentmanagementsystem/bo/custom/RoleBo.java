package com.assignment.resturentmanagementsystem.bo.custom;

import com.assignment.resturentmanagementsystem.Dto.RoleDTO;
import com.assignment.resturentmanagementsystem.bo.SuperBo;
import com.assignment.resturentmanagementsystem.dao.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface RoleBo extends SuperBo {
    ArrayList<RoleDTO> getAll() throws SQLException, ClassNotFoundException;
    boolean Save(RoleDTO DTO) throws SQLException, ClassNotFoundException;
    boolean update(RoleDTO DTO) throws SQLException, ClassNotFoundException;
    boolean Delete(int Id) throws SQLException, ClassNotFoundException;
}
