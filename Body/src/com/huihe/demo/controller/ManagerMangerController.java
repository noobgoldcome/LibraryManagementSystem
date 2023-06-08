package com.huihe.demo.controller;

import com.huihe.demo.Main;
import com.huihe.demo.entity.Manager;
import com.huihe.demo.entity.Student;
import com.huihe.demo.util.DBUtil;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class ManagerMangerController {
    @FXML
    private TableView<Manager> tableView;
    @FXML
    private TableColumn<Manager, String> account;
    @FXML
    private TableColumn<Manager, String> password;
    @FXML
    public void initialize(){
        getData();
    }
    private void getData(){
        List<Manager> manager = DBUtil.readManager();
        account.setCellValueFactory(new PropertyValueFactory<>("account"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
        tableView.setItems(FXCollections.observableList(manager));
    }
    public void refresh(){
        getData();
    }
    public void showManagerAddManagerView(){
        Main.addView("view/manager-addManager.fxml");
    }
    public void showManagerAlterManagerView(){
        Manager manager = tableView.getSelectionModel().getSelectedItem();
        if(manager != null){
            ManagerAlterManagerController.setCurrent(manager);
            Main.addView("view/manager-alterManager.fxml");
        }
    }

    public void doDelete(){
        Manager manager = tableView.getSelectionModel().getSelectedItem();
        if(manager != null){
            DBUtil.delete(manager);
            getData();
        }
    }
}
