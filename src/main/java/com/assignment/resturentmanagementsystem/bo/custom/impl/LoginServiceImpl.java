package com.assignment.resturentmanagementsystem.bo.custom.impl;

import com.assignment.resturentmanagementsystem.Dto.LoginDto;
import com.assignment.resturentmanagementsystem.dao.DaoFactory;
import com.assignment.resturentmanagementsystem.dao.custom.LoginDao;
import com.assignment.resturentmanagementsystem.entity.LoginEntity;
import com.assignment.resturentmanagementsystem.bo.custom.LoginBo;

import java.sql.SQLException;

public class LoginServiceImpl implements LoginBo {

    private final LoginDao loginDao = (LoginDao) DaoFactory.getInstance().getDao(DaoFactory.ServiceType.LOGIN);

    public LoginServiceImpl() {
        //loginDao = (LoginDao) DaoFactory.getInstance().getSuperDao(DaoFactory.ServiceType.LOGIN);
    }


    @Override
    public boolean authenticate(LoginDto loginDto) throws SQLException, ClassNotFoundException {

        LoginEntity loginEntity = new LoginEntity(
                loginDto.getUsername(),
                loginDto.getPassword()
        );

        return loginDao.authenticate(loginEntity);

    }

    @Override
    public boolean authenticateUsername(String enteredUsername) throws SQLException, ClassNotFoundException {

       return loginDao.authenticateUsername(enteredUsername);

    }
}
