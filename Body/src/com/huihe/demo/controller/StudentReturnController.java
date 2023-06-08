package com.huihe.demo.controller;

import com.huihe.demo.Main;
import com.huihe.demo.entity.Book;
import com.huihe.demo.entity.Log;
import com.huihe.demo.util.DBUtil;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.rmi.CORBA.Util;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class StudentReturnController {

    @FXML
    private TableView<Log> tableView;
    @FXML
    private TableColumn<Book,String> isbn;
    @FXML
    private TableColumn<Book,String> name;
    @FXML
    private TableColumn<Book,String> borrowDate;
    @FXML
    private TableColumn<Book,String> returnDate;
    @FXML
    public void initialize(){
        getData();
    }
    private void getData(){
        List<Log> log = DBUtil.readLog();
        isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        borrowDate.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        returnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        tableView.setItems(FXCollections.observableList(log));
    }

    @FXML
    public void doReturn(){
        Log log = tableView.getSelectionModel().getSelectedItem();
        if(log != null){
            DBUtil.delete(log);
            getData();
            DBUtil.updateReturn(log.getIsbn());
        }
    }


}
