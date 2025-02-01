package com.assignment.resturentmanagementsystem.bo.custom.impl;

import com.assignment.resturentmanagementsystem.Dto.FeedbackDTO;
import com.assignment.resturentmanagementsystem.bo.custom.FeedbackBo;
import com.assignment.resturentmanagementsystem.dao.DaoFactory;
import com.assignment.resturentmanagementsystem.dao.custom.FeedbackDao;
import com.assignment.resturentmanagementsystem.dao.custom.impl.FeedbackDaoImpl;
import com.assignment.resturentmanagementsystem.entity.FeedbackEntity;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

public class FeedbackBoImpl implements FeedbackBo {

    FeedbackDao feedbackDao = (FeedbackDao) DaoFactory.getInstance().getDao(DaoFactory.ServiceType.FEEDBACK);

    @Override
    public ArrayList<FeedbackDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<FeedbackEntity> feedbackEntities = feedbackDao.getAll();
        ArrayList<FeedbackDTO> feedbackDTOS = new ArrayList<>();

        for (FeedbackEntity feedbackEntity : feedbackEntities){
            feedbackDTOS.add(new FeedbackDTO(feedbackEntity.getFeedbackID(),
                    feedbackEntity.getDescription(),
                    feedbackEntity.getCustomerID())
            );
        }
        return feedbackDTOS;
    }

    @Override
    public boolean Save(FeedbackDTO DTO) throws SQLException, ClassNotFoundException {
       return feedbackDao.Save(new FeedbackEntity(DTO.getFeedbackID(),
               DTO.getDescription(),
               DTO.getCustomerID())
       );
    }


    @Override
    public boolean update(FeedbackDTO DTO) throws SQLException, ClassNotFoundException {
        return feedbackDao.update(new FeedbackEntity(DTO.getFeedbackID(),
                DTO.getDescription(),
                DTO.getCustomerID())
        );
    }

    @Override
    public boolean Delete(int Fee_Id) throws SQLException, ClassNotFoundException {
        return feedbackDao.Delete(Fee_Id);
    }

    public ObservableList<String> getAllFeedbackIds() throws SQLException, ClassNotFoundException{
        return feedbackDao.getAllFeedbackIds();
    }
}
