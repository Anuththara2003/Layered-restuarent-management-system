package com.assignment.resturentmanagementsystem.bo.custom;

import com.assignment.resturentmanagementsystem.Dto.InventoryDTO;
import com.assignment.resturentmanagementsystem.bo.SuperBo;
import com.assignment.resturentmanagementsystem.dao.CrudUtil;
import com.assignment.resturentmanagementsystem.dao.SuperDao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface InventoryBo extends SuperBo {
     ArrayList<InventoryDTO> getAll() throws SQLException, ClassNotFoundException ;
     boolean Save(InventoryDTO inventoryDTO) throws SQLException, ClassNotFoundException ;
     boolean update(InventoryDTO inventoryDTO) throws SQLException, ClassNotFoundException ;
     boolean Delete(int Id ) throws SQLException, ClassNotFoundException ;

}
