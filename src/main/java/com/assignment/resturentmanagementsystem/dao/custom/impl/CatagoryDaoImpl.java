package com.assignment.resturentmanagementsystem.dao.custom.impl;

import com.assignment.resturentmanagementsystem.Dto.CategoryDTO;
import com.assignment.resturentmanagementsystem.Dto.CustomerDto;
import com.assignment.resturentmanagementsystem.dao.CrudUtil;
import com.assignment.resturentmanagementsystem.dao.custom.CatagoryDao;
import com.assignment.resturentmanagementsystem.dao.custom.CustomerDao;
import com.assignment.resturentmanagementsystem.entity.CategoryEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CatagoryDaoImpl implements CatagoryDao {

    @Override
    public ArrayList<CategoryEntity> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select * from category");

        ArrayList<CategoryEntity> categoryEntities = new ArrayList<>();

        while (rst.next()) {
            CategoryEntity categoryEntity = new CategoryEntity(
                    rst.getInt(1),
                    rst.getString(2)




            );
            categoryEntities.add(categoryEntity);
        }
        return categoryEntities;
    }

    @Override
    public boolean Save(CategoryEntity Dto) throws SQLException, ClassNotFoundException {
        boolean isSaved =  CrudUtil.execute(
                "insert into category values (?,?)",

                Dto.getCatID(),
                Dto.getCatName()
        );

        return isSaved;
    }

    @Override
    public boolean update(CategoryEntity Dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "update category set  Name=?  where Cat_Id=?",


                Dto.getCatName(),
                Dto.getCatID()

        );
    }

    @Override
    public boolean Delete(int Id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from category where Cat_Id=?", Id);
    }
}
