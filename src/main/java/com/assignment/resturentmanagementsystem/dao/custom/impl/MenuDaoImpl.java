package com.assignment.resturentmanagementsystem.dao.custom.impl;

import com.assignment.resturentmanagementsystem.Dto.MenuDTO;
import com.assignment.resturentmanagementsystem.dao.CrudUtil;
import com.assignment.resturentmanagementsystem.dao.custom.MenuDao;
import com.assignment.resturentmanagementsystem.entity.MenuEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MenuDaoImpl implements MenuDao {
    @Override
    public ArrayList<MenuEntity> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select * from menuitem");

        ArrayList<MenuEntity> menuEntities = new ArrayList<>();

        while (rst.next()) {
            MenuEntity menuEntity = new MenuEntity(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getDouble(4),
                    rst.getInt(5)



            );
            menuEntities.add(menuEntity);
        }
        return menuEntities;
    }

    @Override
    public boolean Save(MenuEntity Dto) throws SQLException, ClassNotFoundException {
        boolean isSaved =  CrudUtil.execute(
                "insert into menuitem values (?,?,?,?,?)",

                Dto .getMenuId(),
                Dto.getMenuName(),
                Dto.getCratagoryId(),
                Dto.getPrice(),
                Dto.getQty()

        );

        return isSaved;
    }

    @Override
    public boolean update(MenuEntity Dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "update menuitem set Name=?, Cat_Id=?, unitprice=?,  qty=? where Item_Id=?",


                Dto.getMenuName(),
                Dto.getCratagoryId(),
                Dto.getPrice(),
                Dto.getQty(),
                Dto.getMenuId()

        );
    }

    @Override
    public boolean Delete(int Id) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute("delete from menuitem where Item_Id=?", Id);
    }

    public boolean reduceQentity(int itemId, int quantity) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute("UPDATE menuitem  SET qty = qty - ? WHERE Item_Id = ?;",quantity,itemId);
    }

    @Override
    public ArrayList<String> getAllItemIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select Item_Id from menuitem");

        ArrayList<String> itemIds = new ArrayList<>();

        while (rst.next()){
            itemIds.add(rst.getString(1));
        }

        return itemIds;
    }

    public MenuDTO findById(String selectedItemId) throws SQLException, ClassNotFoundException {
        // Execute SQL query to find the item by ID
        ResultSet rst = CrudUtil.execute("select * from menuitem where Item_Id=?", selectedItemId);

        // If the item is found, create an ItemDTO object with the retrieved data
        if (rst.next()) {
            MenuDTO menuDTO = new MenuDTO(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getDouble(4),
                    rst.getInt(5)
            );
            return menuDTO;
        }
        return null;
    }
}
