package com.assignment.resturentmanagementsystem.bo.custom.impl;

import com.assignment.resturentmanagementsystem.Dto.InventoryDTO;
import com.assignment.resturentmanagementsystem.bo.custom.InventoryBo;
import com.assignment.resturentmanagementsystem.dao.DaoFactory;
import com.assignment.resturentmanagementsystem.dao.custom.InventoryDao;
import com.assignment.resturentmanagementsystem.entity.InventoryEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryBoImpl implements InventoryBo {

    InventoryDao inventoryDao = (InventoryDao) DaoFactory.getInstance().getDao(DaoFactory.ServiceType.INVENTORY);
    @Override
    public ArrayList<InventoryDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<InventoryEntity>inventoryEntities = inventoryDao.getAll();
        ArrayList<InventoryDTO> inventoryDTOS = new ArrayList<>();

        for (InventoryEntity inventoryEntity: inventoryEntities){
            inventoryDTOS.add(new InventoryDTO(inventoryEntity.getInventoryId(),
                    inventoryEntity.getDescription(),
                    inventoryEntity.getBranchId())
            );
        }
        return inventoryDTOS;
    }

    @Override
    public boolean Save(InventoryDTO inventoryDTO) throws SQLException, ClassNotFoundException {
     return inventoryDao.Save(new InventoryEntity(inventoryDTO.getInventoryId(),
             inventoryDTO.getDescription(),
             inventoryDTO.getBranchId())
     );
    }

    @Override
    public boolean update(InventoryDTO inventoryDTO) throws SQLException, ClassNotFoundException {
        return inventoryDao.update(new InventoryEntity(inventoryDTO.getInventoryId(),
                inventoryDTO.getDescription(),
                inventoryDTO.getBranchId())
        );
    }

    @Override
    public boolean Delete(int Inven_Id) throws SQLException, ClassNotFoundException {
        return inventoryDao.Delete(Inven_Id);
    }
}
