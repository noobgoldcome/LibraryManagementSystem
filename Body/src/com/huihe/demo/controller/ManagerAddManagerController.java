package com.huihe.demo.controller;

import com.huihe.demo.entity.Manager;
import com.huihe.demo.entity.Student;
import com.huihe.demo.util.DBUtil;
import com.huihe.demo.util.StringUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ManagerAddManagerController {
    @FXML
    private TextField account;
    @FXML
    private PasswordField password1;
    @FXML
    private PasswordField password2;
    @FXML
    private Label errorinfo;

    @FXML
    public void initialize(){

    }
    @FXML
    public void addManager(){
        String accountText = account.getText();
        String passwordText1 = password1.getText();
        String passwordText2 = password2.getText();
        if(StringUtil.isEmpty(accountText)){
            errorinfo.setText("请输入账户!!");
            errorinfo.setVisible(true);
            return;
        }
        if(StringUtil.isEmpty(passwordText1)){
            errorinfo.setText("请输入密码!!");
            errorinfo.setVisible(true);
            return;
        }
        if(StringUtil.isEmpty(passwordText2)){
            errorinfo.setText("请确认密码!!");
            errorinfo.setVisible(true);
            return;
        }
        if(!passwordText1.equals(passwordText2)){
            errorinfo.setText("请保证两次密码输入一致！！");
            errorinfo.setVisible(true);
            return;
        }else{
            Manager manager = new Manager(accountText, passwordText1);
            DBUtil.add(manager);
            System.out.println("添加成功" + accountText);
        }

    }
}
