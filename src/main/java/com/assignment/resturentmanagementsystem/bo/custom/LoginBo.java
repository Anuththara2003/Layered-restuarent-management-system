package com.assignment.resturentmanagementsystem.bo.custom;

import com.assignment.resturentmanagementsystem.Dto.LoginDto;
import com.assignment.resturentmanagementsystem.bo.SuperBo;

import java.sql.SQLException;

public interface LoginBo extends SuperBo {
    boolean authenticate(LoginDto loginDto) throws SQLException, ClassNotFoundException;

    boolean authenticateUsername(String enteredUsername) throws SQLException, ClassNotFoundException;
}
