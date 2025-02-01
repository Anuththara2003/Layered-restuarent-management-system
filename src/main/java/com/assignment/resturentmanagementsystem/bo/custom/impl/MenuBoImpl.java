package com.assignment.resturentmanagementsystem.bo.custom.impl;

import com.assignment.resturentmanagementsystem.Dto.MenuDTO;
import com.assignment.resturentmanagementsystem.bo.custom.MenuBo;
import com.assignment.resturentmanagementsystem.dao.CrudUtil;
import com.assignment.resturentmanagementsystem.dao.DaoFactory;
import com.assignment.resturentmanagementsystem.dao.custom.MenuDao;
import com.assignment.resturentmanagementsystem.entity.MenuEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MenuBoImpl implements MenuBo {

      MenuDao menuDao = (MenuDao) DaoFactory.getInstance().getDao(DaoFactory.ServiceType.MENU);
    @Override
    public ArrayList<MenuDTO> getAll() throws SQLException, ClassNotFoundException {
       ArrayList<MenuEntity>menuEntities = menuDao.getAll();
       ArrayList<MenuDTO> menuDTOS = new ArrayList<>();

       for (MenuEntity menuEntity : menuEntities){
           menuDTOS.add(new MenuDTO(menuEntity.getMenuId(),
                   menuEntity.getMenuName(),
                   menuEntity.getCratagoryId(),
                   menuEntity.getPrice(),
                   menuEntity.getQty())
           );
       }
       return menuDTOS;
    }

    @Override
    public boolean Save(MenuDTO DTO) throws SQLException, ClassNotFoundException {
       return menuDao.Save(new MenuEntity(DTO.getMenuId(),
               DTO.getMenuName(),
               DTO.getCratagoryId(),
               DTO.getPrice(),
               DTO.getQty()));
    }

    @Override
    public boolean update(MenuDTO DTO) throws SQLException, ClassNotFoundException {
        return menuDao.update(new MenuEntity(DTO.getMenuId(),
                DTO.getMenuName(),
                DTO.getCratagoryId(),
                DTO.getPrice(),
                DTO.getQty()));
    }

    @Override
    public boolean Delete(int Id) throws SQLException, ClassNotFoundException {
        return menuDao.Delete(Id);
    }

    public ArrayList<String> getAllItemIds() throws SQLException, ClassNotFoundException {
        return menuDao.getAllItemIds();
    }

    public MenuDTO findById(String selectedItemId) throws SQLException, ClassNotFoundException {
        return menuDao.findById(selectedItemId);
    }
}
