package com.assignment.resturentmanagementsystem.bo.custom;

import com.assignment.resturentmanagementsystem.Dto.ResevationDTO;
import com.assignment.resturentmanagementsystem.bo.SuperBo;
import com.assignment.resturentmanagementsystem.dao.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ReservationBO extends SuperBo {

     ArrayList<ResevationDTO> getAll() throws SQLException, ClassNotFoundException ;
     boolean Save(ResevationDTO DTO) throws SQLException, ClassNotFoundException ;
     boolean update(ResevationDTO DTO) throws SQLException, ClassNotFoundException ;
     boolean Delete(int Id ) throws SQLException, ClassNotFoundException;
}
