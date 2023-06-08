package com.huihe.demo.controller;

import com.huihe.demo.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ManagerMainController {
    @FXML
    private Label name1;
    public void initialize(){
        setName();
    }
    private void setName(){
        name1.setText(Main.account);
    }
    @FXML
    public void showManageBookView(){
        Main.addView("view/manager-book.fxml");
        System.out.println(Main.getAccount());
    }
    public void showManageStudentView(){
        Main.addView("view/manager-student.fxml");
    }

    public void showManageManagerView(){
        Main.addView("view/manager-manager.fxml");
    }
    public void back(){
        Main.changeView("view/manager-login.fxml");
    }
}
