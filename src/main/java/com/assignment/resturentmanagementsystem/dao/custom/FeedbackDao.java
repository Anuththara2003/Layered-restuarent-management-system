package com.assignment.resturentmanagementsystem.dao.custom;

import com.assignment.resturentmanagementsystem.Dto.FeedbackDTO;
import com.assignment.resturentmanagementsystem.dao.CrudDao;
import com.assignment.resturentmanagementsystem.entity.FeedbackEntity;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface FeedbackDao extends CrudDao<FeedbackEntity> {
    ObservableList<String> getAllFeedbackIds() throws SQLException, ClassNotFoundException;
}
