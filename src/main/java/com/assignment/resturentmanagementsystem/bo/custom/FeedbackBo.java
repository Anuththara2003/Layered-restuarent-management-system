package com.assignment.resturentmanagementsystem.bo.custom;

import com.assignment.resturentmanagementsystem.Dto.FeedbackDTO;
import com.assignment.resturentmanagementsystem.bo.SuperBo;
import com.assignment.resturentmanagementsystem.dao.CrudUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface FeedbackBo extends SuperBo {

     ArrayList<FeedbackDTO> getAll() throws SQLException, ClassNotFoundException ;
     boolean Save(FeedbackDTO DTO) throws SQLException, ClassNotFoundException;
     boolean update(FeedbackDTO DTO) throws SQLException, ClassNotFoundException;
     boolean Delete(int Fee_Id ) throws SQLException, ClassNotFoundException ;
     ObservableList<String> getAllFeedbackIds() throws SQLException, ClassNotFoundException;

}
