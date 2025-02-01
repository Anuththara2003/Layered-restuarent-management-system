package com.assignment.resturentmanagementsystem.dao.custom.impl;

import com.assignment.resturentmanagementsystem.Dto.BranchDto;
import com.assignment.resturentmanagementsystem.dao.CrudUtil;
import com.assignment.resturentmanagementsystem.dao.custom.BranchDao;
import com.assignment.resturentmanagementsystem.entity.BranchEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BranchDaoImpl implements BranchDao {


    @Override
    public ArrayList<BranchEntity> getAll() throws SQLException, ClassNotFoundException {

        ResultSet rst = CrudUtil.execute("select * from branch");

        ArrayList<BranchEntity> branchDtos = new ArrayList<>();

        while (rst.next()) {
            BranchEntity branchEntity = new BranchEntity(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4)


            );
            branchDtos.add(branchEntity);
        }
        return branchDtos;
    }


    @Override
    public boolean Save(BranchEntity Dto) throws SQLException, ClassNotFoundException {
        boolean isSaved =  CrudUtil.execute(
                "insert into branch values (?,?,?,?)",

                Dto.getBranchId(),
                Dto.getBranchName(),
                Dto.getBranchAddress(),
                Dto.getEmployeeId()

        );

        return isSaved;
    }

    @Override
    public boolean update(BranchEntity Dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "update branch set Name=?, Location=?, Location=? where Branch_Id=?",

                Dto.getBranchAddress(),
                Dto.getEmployeeId(),
                Dto.getBranchName(),
                Dto.getBranchId()

        );
    }

    @Override
    public boolean Delete(int Id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from branch where Branch_Id=?", Id);
    }


    public ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select Branch_Id from branch");

        ArrayList<String> branchIds = new ArrayList<>();

        while (rst.next()) {
            branchIds.add(rst.getString(1));
        }

        return branchIds;
    }

}
