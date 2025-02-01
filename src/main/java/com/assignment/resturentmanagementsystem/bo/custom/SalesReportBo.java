package com.assignment.resturentmanagementsystem.bo.custom;

import com.assignment.resturentmanagementsystem.Dto.SalesReportDTO;
import com.assignment.resturentmanagementsystem.bo.SuperBo;
import com.assignment.resturentmanagementsystem.dao.CrudDao;
import com.assignment.resturentmanagementsystem.dao.CrudUtil;
import com.assignment.resturentmanagementsystem.dao.SuperDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface SalesReportBo extends SuperBo {
     ArrayList<SalesReportDTO> getAll() throws SQLException, ClassNotFoundException ;
     boolean Save(SalesReportDTO DTO) throws SQLException, ClassNotFoundException ;
     boolean update(SalesReportDTO DTO) throws SQLException, ClassNotFoundException ;
     boolean Delete(int Id ) throws SQLException, ClassNotFoundException ;

}
