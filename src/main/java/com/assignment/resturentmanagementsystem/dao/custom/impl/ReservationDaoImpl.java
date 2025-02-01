package com.assignment.resturentmanagementsystem.dao.custom.impl;

import com.assignment.resturentmanagementsystem.Dto.ResevationDTO;
import com.assignment.resturentmanagementsystem.dao.CrudUtil;
import com.assignment.resturentmanagementsystem.dao.custom.ReservationDao;
import com.assignment.resturentmanagementsystem.entity.ReservationEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationDaoImpl implements ReservationDao {

    @Override
    public ArrayList<ReservationEntity> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select * from reservation");

        ArrayList<ReservationEntity> reservationEntities = new ArrayList<>();

        while (rst.next()) {
            ReservationEntity reservationEntity = new ReservationEntity(
                    rst.getInt(1),
                    rst.getInt(2),
                    rst.getString(3)



            );
            reservationEntities.add(reservationEntity);
        }
        return reservationEntities;
    }

    @Override
    public boolean Save(ReservationEntity Dto) throws SQLException, ClassNotFoundException {
        boolean isSaved =  CrudUtil.execute(
                "insert into reservation values (?,?,?)",

                Dto.getReservationId(),
                Dto.getCustomerId(),
                Dto.getDescription()
        );

        return isSaved;
    }

    @Override
    public boolean update(ReservationEntity Dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "update reservation set Cust_Id=?, Description=?  where Res_Id=?",


                Dto.getCustomerId(),
                Dto.getDescription(),
                Dto.getReservationId()

        );
    }

    @Override
    public boolean Delete(int Id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from reservation where Res_Id=?", Id);
    }
}
