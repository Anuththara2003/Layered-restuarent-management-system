package com.assignment.resturentmanagementsystem.dao.custom.impl;

import com.assignment.resturentmanagementsystem.Dto.RoleDTO;
import com.assignment.resturentmanagementsystem.dao.CrudUtil;
import com.assignment.resturentmanagementsystem.dao.custom.RoleDao;
import com.assignment.resturentmanagementsystem.entity.RoleEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoleDaoImpl implements RoleDao {
    @Override
    public ArrayList<RoleEntity> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select * from role");

        ArrayList<RoleEntity> roleEntities = new ArrayList<>();

        while (rst.next()) {
            RoleEntity roleEntity = new RoleEntity(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getInt(3)




            );
            roleEntities.add(roleEntity);
        }
        return roleEntities;
    }

    @Override
    public boolean Save(RoleEntity Dto) throws SQLException, ClassNotFoundException {
        boolean isSaved =  CrudUtil.execute(
                "insert into role values (?,?,?)",

                Dto.getRoleId(),
                Dto.getRoleName(),
                Dto.getEmployeeId()

        );

        return isSaved;
    }

    @Override
    public boolean update(RoleEntity Dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "UPDATE role SET  Name=?, Emp_Id=? WHERE Role_Id=?",

                Dto.getRoleName(),
                Dto.getEmployeeId(),
                Dto.getRoleId()
        );
    }

    @Override
    public boolean Delete(int Id) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute("delete from role where Role_Id=?", Id);
    }
}
