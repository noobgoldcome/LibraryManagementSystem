package com.huihe.demo.controller;

import com.huihe.demo.Main;
import com.huihe.demo.util.DBUtil;
import com.huihe.demo.util.StringUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ManagerLogInController {
    @FXML
    private TextField account;

    @FXML
    private PasswordField password;

    @FXML
    private Label errorinfo;

    @FXML
    public void initialize(){

    }
    @FXML
    public void doLogin(){
        String accountText = account.getText();
        String passwordText = password.getText();
        if(StringUtil.isEmpty(accountText)){
            errorinfo.setText("请输入账户!!");
            errorinfo.setVisible(true);
            return;
        }
        if(StringUtil.isEmpty(passwordText)){
            errorinfo.setText("请输入密码!!");
            errorinfo.setVisible(true);
            return;
        }

        if(DBUtil.selectManager(accountText,passwordText)){
            //进入系统
            System.out.println("欢迎" + accountText);
            Main.setAccount(accountText);
            Main.changeView("view/manager-main.fxml");
        }else{
            errorinfo.setText("账号或密码错误!!");
            errorinfo.setVisible(true);
        }
    }

    public void back(){
        Main.changeView("view/entrance.fxml");
    }

}
