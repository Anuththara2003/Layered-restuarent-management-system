package com.assignment.resturentmanagementsystem.dao.custom;

import com.assignment.resturentmanagementsystem.Dto.LoginDto;
import com.assignment.resturentmanagementsystem.dao.CrudDao;
import com.assignment.resturentmanagementsystem.entity.LoginEntity;

import java.sql.SQLException;

public interface LoginDao extends CrudDao<LoginEntity> {
    boolean authenticate(LoginEntity loginEntity) throws SQLException, ClassNotFoundException;
    boolean authenticateUsername(String enteredUsername) throws SQLException, ClassNotFoundException;
}
