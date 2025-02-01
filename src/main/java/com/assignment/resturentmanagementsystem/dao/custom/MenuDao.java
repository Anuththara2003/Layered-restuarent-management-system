package com.assignment.resturentmanagementsystem.dao.custom;

import com.assignment.resturentmanagementsystem.Dto.MenuDTO;
import com.assignment.resturentmanagementsystem.dao.CrudDao;
import com.assignment.resturentmanagementsystem.entity.MenuEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MenuDao extends CrudDao<MenuEntity> {
    boolean reduceQentity(int itemId, int quantity) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllItemIds() throws SQLException, ClassNotFoundException;
    MenuDTO findById(String selectedItemId) throws SQLException, ClassNotFoundException;
}
