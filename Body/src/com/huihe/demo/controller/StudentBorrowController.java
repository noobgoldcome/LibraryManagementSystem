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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class StudentBorrowController {

    @FXML
    private TableView<Book> tableView;
    @FXML
    private TableColumn<Book,String> isbn;
    @FXML
    private TableColumn<Book,String> name;
    @FXML
    private TableColumn<Book,String> author;
    @FXML
    private TableColumn<Book,String> publishDate;
    @FXML
    private TableColumn<Book,String> publisher;
    @FXML
    private TableColumn<Book,Integer> available;
    @FXML
    public void initialize(){
        getData();
    }
    private void getData(){
        List<Book> book = DBUtil.readData();
        isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        publishDate.setCellValueFactory(new PropertyValueFactory<>("publishDate"));
        publisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        available.setCellValueFactory(new PropertyValueFactory<>("available"));
        tableView.setItems(FXCollections.observableList(book));
    }

    @FXML
    public void doBorrow(){
        Book book = tableView.getSelectionModel().getSelectedItem();
        if(book != null){
            LocalDate borrowDate = LocalDate.now();//将借书记录到log表中
            LocalDate returnDate = borrowDate.plusDays(15);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String b = borrowDate.format(formatter);
            String r = returnDate.format(formatter);
            Log log = new Log(Main.getAccount(), book.getIsbn(), book.getName(),b, r);
            DBUtil.add(log);
            DBUtil.updateBorrow(book);
            getData();
        }
    }


}
