package com.assignment.resturentmanagementsystem.bo.custom.impl;

import com.assignment.resturentmanagementsystem.Dto.ResevationDTO;
import com.assignment.resturentmanagementsystem.bo.custom.ReservationBO;
import com.assignment.resturentmanagementsystem.dao.DaoFactory;
import com.assignment.resturentmanagementsystem.dao.custom.ReservationDao;
import com.assignment.resturentmanagementsystem.dao.custom.impl.ReservationDaoImpl;
import com.assignment.resturentmanagementsystem.entity.ReservationEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationBoImpl implements ReservationBO {
    ReservationDao reservationDao = (ReservationDao) DaoFactory.getInstance().getDao(DaoFactory.ServiceType.RESERVATION);
    @Override
    public ArrayList<ResevationDTO> getAll() throws SQLException, ClassNotFoundException {
      ArrayList<ReservationEntity> reservationEntities = reservationDao.getAll();
      ArrayList<ResevationDTO> resevationDTOS = new ArrayList<>();

      for(ReservationEntity reservationEntity : reservationEntities){
          resevationDTOS.add(new ResevationDTO(reservationEntity.getReservationId(),
                  reservationEntity.getCustomerId(),
                  reservationEntity.getDescription())
          );
      }
      return resevationDTOS;
    }

    @Override
    public boolean Save(ResevationDTO DTO) throws SQLException, ClassNotFoundException {
        return reservationDao.Save(new ReservationEntity(DTO.getReservationId(),
                DTO.getCustomerId(),
                DTO.getDescription())
        );
    }

    @Override
    public boolean update(ResevationDTO DTO) throws SQLException, ClassNotFoundException {
        return reservationDao.update(new ReservationEntity(DTO.getReservationId(),
                DTO.getCustomerId(),
                DTO.getDescription())
        );
    }

    @Override
    public boolean Delete(int Id) throws SQLException, ClassNotFoundException {
        return reservationDao.Delete(Id);
    }
}
