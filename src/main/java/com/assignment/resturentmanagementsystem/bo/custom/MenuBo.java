package com.assignment.resturentmanagementsystem.bo.custom;

import com.assignment.resturentmanagementsystem.Dto.MenuDTO;
import com.assignment.resturentmanagementsystem.bo.SuperBo;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MenuBo extends SuperBo {
    ArrayList<MenuDTO> getAll() throws SQLException, ClassNotFoundException;
    boolean Save(MenuDTO DTO) throws SQLException, ClassNotFoundException;
    boolean update(MenuDTO DTO) throws SQLException, ClassNotFoundException;
    boolean Delete(int Id) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllItemIds() throws SQLException, ClassNotFoundException;
    MenuDTO findById(String selectedItemId) throws SQLException, ClassNotFoundException;
}
