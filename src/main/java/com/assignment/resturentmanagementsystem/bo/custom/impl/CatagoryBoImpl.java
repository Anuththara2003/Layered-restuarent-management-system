package com.assignment.resturentmanagementsystem.bo.custom.impl;

import com.assignment.resturentmanagementsystem.Dto.CategoryDTO;
import com.assignment.resturentmanagementsystem.bo.BoFactory;
import com.assignment.resturentmanagementsystem.bo.custom.CatagoryBO;
import com.assignment.resturentmanagementsystem.dao.DaoFactory;
import com.assignment.resturentmanagementsystem.dao.custom.CatagoryDao;
import com.assignment.resturentmanagementsystem.entity.CategoryEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class CatagoryBoImpl implements CatagoryBO {

    CatagoryDao catagoryDao = (CatagoryDao) DaoFactory.getInstance().getDao(DaoFactory.ServiceType.CATEGORY);

    @Override
    public ArrayList<CategoryDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<CategoryEntity> categoryEntities = catagoryDao.getAll();
        ArrayList<CategoryDTO> categoryDTOS = new ArrayList<>();

        for (CategoryEntity categoryEntity : categoryEntities) {
            categoryDTOS.add(new CategoryDTO(categoryEntity.getCatID(),
                    categoryEntity.getCatName())
            );
        }
        return categoryDTOS;
    }

    @Override
    public boolean Save(CategoryDTO categoryDTO) throws SQLException, ClassNotFoundException {
        return catagoryDao.Save(new CategoryEntity(
                categoryDTO.getCatID(),
                categoryDTO.getCatName())
        );
    }

    @Override
    public boolean update(CategoryDTO categoryDTO) throws SQLException, ClassNotFoundException {
        return catagoryDao.update(new CategoryEntity(
                categoryDTO.getCatID(),
                categoryDTO.getCatName()));
    }

    @Override
    public boolean Delete(int Cat_Id) throws SQLException, ClassNotFoundException {
        return catagoryDao.Delete(Cat_Id);
    }
}
