package com.assignment.resturentmanagementsystem.dao.custom.impl;

import com.assignment.resturentmanagementsystem.Dto.InventoryDTO;
import com.assignment.resturentmanagementsystem.dao.CrudUtil;
import com.assignment.resturentmanagementsystem.dao.custom.InventoryDao;
import com.assignment.resturentmanagementsystem.entity.InventoryEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryDaoImpl implements InventoryDao {
    @Override
    public ArrayList<InventoryEntity> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select * from inventory");

        ArrayList<InventoryEntity> inventoryEntities = new ArrayList<>();

        while (rst.next()) {
            InventoryEntity inventoryEntity = new InventoryEntity(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getInt(3)




            );
            inventoryEntities.add(inventoryEntity);
        }
        return inventoryEntities;
    }

    @Override
    public boolean Save(InventoryEntity Dto) throws SQLException, ClassNotFoundException {
        boolean isSaved =  CrudUtil.execute(
                "insert into inventory values (?,?,?)",

                Dto.getInventoryId(),
                Dto.getDescription(),
                Dto.getBranchId()
        );

        return isSaved;
    }

    @Override
    public boolean update(InventoryEntity Dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "update inventory set Description=?, Branch_Id=?  where Inven_Id=?",



                Dto.getDescription(),
                Dto.getBranchId(),
                Dto.getInventoryId()

        );
    }

    @Override
    public boolean Delete(int Id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from inventory where Inven_Id=?", Id);
    }


}
