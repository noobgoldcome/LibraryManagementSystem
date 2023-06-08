package com.huihe.demo.controller;

import com.huihe.demo.Main;
import com.huihe.demo.entity.Book;
import com.huihe.demo.entity.Student;
import com.huihe.demo.util.DBUtil;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class ManagerStudentController {
    @FXML
    private TableView<Student> tableView;
    @FXML
    private TableColumn<Student, String> account;
    @FXML
    private TableColumn<Student, String> password;
    @FXML
    public void initialize(){
        getData();
    }
    private void getData(){
        List<Student> student = DBUtil.readStudent();
        account.setCellValueFactory(new PropertyValueFactory<>("account"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
        tableView.setItems(FXCollections.observableList(student));
    }
    public void refresh(){
        getData();
    }
    public void showManagerAddStudentView(){
        Main.addView("view/manager-addStudent.fxml");
    }
    public void showManagerAlterStudentView(){
        Student student = tableView.getSelectionModel().getSelectedItem();
        if(student != null){
            ManagerAlterStudentController.setCurrent(student);
            Main.addView("view/manager-alterStudent.fxml");
        }
    }

    public void doDelete(){
        Student student = tableView.getSelectionModel().getSelectedItem();
        if(student != null){
            DBUtil.delete(student);
            getData();
        }
    }
}
