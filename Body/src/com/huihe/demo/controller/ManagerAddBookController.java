package com.huihe.demo.controller;

import com.huihe.demo.entity.Book;
import com.huihe.demo.util.DBUtil;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ManagerAddBookController {
    @FXML
    private TextField isbn;
    @FXML
    private TextField name;
    @FXML
    private TextField author;
    @FXML
    private DatePicker publishDate;
    @FXML
    private TextField publisher;
    @FXML
    private TextField available;
    @FXML
    public void initialize(){

    }
    @FXML
    public void addBookToDB(){
        Book book = new Book(isbn.getText(),name.getText(),author.getText(),publishDate.getEditor().getText(),publisher.getText(),Integer.parseInt(available.getText()));
//        FileUtil.addData(staff);
        DBUtil.add(book);

    }
}
