package com.assignment.resturentmanagementsystem.dao;

import com.assignment.resturentmanagementsystem.Dto.BranchDto;
import com.assignment.resturentmanagementsystem.Dto.LoginDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDao<T> extends SuperDao{
    ArrayList<T> getAll() throws SQLException, ClassNotFoundException ;
    boolean Save(T Dto) throws SQLException, ClassNotFoundException ;
    boolean update(T Dto) throws SQLException, ClassNotFoundException ;
    boolean Delete(int Id ) throws SQLException, ClassNotFoundException ;

}
