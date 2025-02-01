package com.assignment.resturentmanagementsystem.dao.custom.impl;

import com.assignment.resturentmanagementsystem.Dto.LoginDto;
import com.assignment.resturentmanagementsystem.dao.CrudUtil;
import com.assignment.resturentmanagementsystem.dao.custom.LoginDao;
import com.assignment.resturentmanagementsystem.entity.LoginEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginDaoImpl implements LoginDao {

    @Override
    public boolean authenticate(LoginEntity loginEntity) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM user WHERE User_Name = ? AND password = ?",
                loginEntity.getUsername(),
                loginEntity.getPassword());

        return result.next();
    }

    @Override
    public boolean authenticateUsername(String enteredUsername) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM user WHERE User_Name = ?", enteredUsername);

        return result.next();
    }

    @Override
    public ArrayList<LoginEntity> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean Save(LoginEntity Dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(LoginEntity Dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean Delete(int Id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
