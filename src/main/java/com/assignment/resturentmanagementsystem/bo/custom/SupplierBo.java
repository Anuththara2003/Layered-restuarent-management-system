package com.assignment.resturentmanagementsystem.bo.custom;

import com.assignment.resturentmanagementsystem.Dto.SupplierDTO;
import com.assignment.resturentmanagementsystem.bo.SuperBo;
import com.assignment.resturentmanagementsystem.dao.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBo extends SuperBo {
     ArrayList<SupplierDTO> getAll() throws SQLException, ClassNotFoundException ;
     boolean Save(SupplierDTO DTO) throws SQLException, ClassNotFoundException ;
    boolean update(SupplierDTO DTO) throws SQLException, ClassNotFoundException ;
   boolean Delete(int Id ) throws SQLException, ClassNotFoundException ;




}
