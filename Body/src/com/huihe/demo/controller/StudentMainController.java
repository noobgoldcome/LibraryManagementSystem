package com.huihe.demo.controller;

import com.huihe.demo.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.awt.*;

public class StudentMainController {
    @FXML
    private Label name;
    public void initialize(){
        setName();
    }
    private void setName(){
        name.setText(Main.account);
    }
    @FXML
    public void showBorrowView(){
        Main.addView("view/student-borrow.fxml");
        System.out.println(Main.getAccount());
    }
    public void showReturnView(){
        Main.addView("view/student-return.fxml");
        System.out.println("还书还书！（仅供测试）");
    }

    public void showAlterPasswordView(){
        Main.addView("view/student-alterPassword.fxml");
    }
    public void back(){
        Main.changeView("view/student-login.fxml");
    }
}
