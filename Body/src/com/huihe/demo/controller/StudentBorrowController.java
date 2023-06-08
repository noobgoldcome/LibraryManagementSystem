package com.huihe.demo.controller;

import com.huihe.demo.Main;
import com.huihe.demo.entity.Book;
import com.huihe.demo.entity.Log;
import com.huihe.demo.util.DBUtil;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class StudentBorrowController {

    @FXML
    private TableView<Book> tableView;
    @FXML
    private TableColumn<Book,Boolean> select;
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
    private TextField search;
    @FXML
    public void initialize(){
        getData();
    }
    private void getData(){
        List<Book> book = DBUtil.readData();
        select.setCellValueFactory(cellData -> cellData.getValue().selectedProperty());
        select.setCellFactory(column -> new TableCell<Book, Boolean>() {
            private final CheckBox checkBox = new CheckBox();

            {
                checkBox.setOnAction(event -> {
                    if (getTableRow() != null) {
                        int index = getTableRow().getIndex();
                        if (index >= 0 && index < tableView.getItems().size()) {
                            Book book = tableView.getItems().get(index);
                            book.setSelected(checkBox.isSelected());
                        }
                    }
                });
            }
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (!empty) {
                    setGraphic(checkBox);
                    if (getTableRow() != null) {
                        int index = getTableRow().getIndex();
                        if (index >= 0 && index < tableView.getItems().size()) {
                            Book book = tableView.getItems().get(index);
                            checkBox.setSelected(book.isSelected());
                        }
                    }
                } else {
                    setGraphic(null);
                }
            }
        });
        isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        publishDate.setCellValueFactory(new PropertyValueFactory<>("publishDate"));
        publisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        available.setCellValueFactory(new PropertyValueFactory<>("available"));
        tableView.setItems(FXCollections.observableList(book));
    }
    private void searchData(String searchText){
        List<Book> book = DBUtil.readData(searchText);
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
        List<Book> selectedBooks = new ArrayList<>();
        ObservableList<Book> allBooks = tableView.getItems();
        for (Book book : allBooks) {
            if (book.isSelected()) {
                selectedBooks.add(book);
            }
        }
        for (Book book : selectedBooks) {
            // 执行借书操作
            borrowOneBook(book);
        }

    }
    private void borrowOneBook(Book book){
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
    @FXML
    public void doSearch(){
        String searchText = search.getText();
        searchData(searchText);
    }
}
