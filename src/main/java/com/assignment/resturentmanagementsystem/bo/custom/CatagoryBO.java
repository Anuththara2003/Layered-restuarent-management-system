package com.assignment.resturentmanagementsystem.bo.custom;

import com.assignment.resturentmanagementsystem.Dto.CategoryDTO;
import com.assignment.resturentmanagementsystem.bo.SuperBo;
import com.assignment.resturentmanagementsystem.dao.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CatagoryBO extends SuperBo {
      ArrayList<CategoryDTO> getAll() throws SQLException, ClassNotFoundException ;
     boolean Save(CategoryDTO DTO) throws SQLException, ClassNotFoundException;
     boolean update(CategoryDTO DTO) throws SQLException, ClassNotFoundException ;
     boolean Delete(int Cat_Id ) throws SQLException, ClassNotFoundException ;
}
