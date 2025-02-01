package com.assignment.resturentmanagementsystem.bo.custom;

import com.assignment.resturentmanagementsystem.Dto.BranchDto;
import com.assignment.resturentmanagementsystem.bo.SuperBo;
import com.assignment.resturentmanagementsystem.dao.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface BranchBo extends SuperBo {


    ArrayList<BranchDto> getAll() throws SQLException, ClassNotFoundException;
    boolean Save(BranchDto Dto) throws SQLException, ClassNotFoundException ;
    boolean update(BranchDto Dto) throws SQLException, ClassNotFoundException ;
    boolean Delete(int Id) throws SQLException, ClassNotFoundException;


}
