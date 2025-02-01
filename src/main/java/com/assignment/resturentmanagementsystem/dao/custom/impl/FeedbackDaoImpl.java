package com.assignment.resturentmanagementsystem.dao.custom.impl;

import com.assignment.resturentmanagementsystem.Dto.FeedbackDTO;
import com.assignment.resturentmanagementsystem.dao.CrudUtil;
import com.assignment.resturentmanagementsystem.dao.custom.FeedbackDao;
import com.assignment.resturentmanagementsystem.entity.FeedbackEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FeedbackDaoImpl implements FeedbackDao {
    @Override
    public ArrayList<FeedbackEntity> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select * from feedback");

        ArrayList<FeedbackEntity> feedbackEntities = new ArrayList<>();

        while (rst.next()) {
            FeedbackEntity feedbackDTO = new FeedbackEntity(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getInt(3)


            );
            feedbackEntities.add(feedbackDTO);
        }
        return feedbackEntities;
    }

    @Override
    public boolean Save(FeedbackEntity Dto) throws SQLException, ClassNotFoundException {
        boolean isSaved =  CrudUtil.execute(
                "insert into feedback values (?,?,?)",

                Dto.getFeedbackID(),
                Dto.getDescription(),
                Dto.getFeedbackID()

        );

        return isSaved;
    }

    @Override
    public boolean update(FeedbackEntity Dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "update feedback set Fee_Id=?, Description=? where Fee_Id=?",

                Dto.getFeedbackID(),
                Dto.getDescription(),
                Dto.getFeedbackID()

        );
    }

    @Override
    public boolean Delete(int Id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from feedback where Fee_Id=?", Id);
    }

    public ObservableList<String> getAllFeedbackIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select Fee_Id from feedback");

        ObservableList<String>feedbackids = FXCollections.observableArrayList();

        while (rst.next()) {
            feedbackids.add(rst.getString(1));
        }

        return feedbackids;
    }
}
