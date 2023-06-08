package com.huihe.demo.controller;

import com.huihe.demo.entity.Book;
import com.huihe.demo.util.DBUtil;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ManagerAlterBookController {
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
    private static Book current;

    public static void setCurrent(Book current){
        ManagerAlterBookController.current = current;
    }
    @FXML
    public void initialize(){
        isbn.setText(current.getIsbn());
        name.setText(current.getName());
        author.setText(current.getAuthor());
        publishDate.getEditor().setText(current.getPublishDate());
        publisher.setText(current.getPublisher());
        available.setText(current.getAvailable() + "");
    }
    @FXML
    public void alterBookToDB(){
        Book book = new Book(isbn.getText(),name.getText(),author.getText(),publishDate.getEditor().getText(),publisher.getText(),Integer.parseInt(available.getText()));
        DBUtil.update(book);

    }
}
