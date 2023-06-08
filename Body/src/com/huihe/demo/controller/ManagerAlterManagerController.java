package com.huihe.demo.controller;

import com.huihe.demo.Main;
import com.huihe.demo.entity.Manager;
import com.huihe.demo.entity.Student;
import com.huihe.demo.util.DBUtil;
import com.huihe.demo.util.StringUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ManagerAlterManagerController {
    @FXML
    private TextField account;
    @FXML
    private PasswordField password1;
    @FXML
    private PasswordField password2;
    @FXML
    private Label errorinfo;
    private static Manager current;

    public static void setCurrent(Manager current){
        ManagerAlterManagerController.current = current;
    }
    @FXML
    public void initialize(){
        account.setText(current.getAccount());
    }

    public void alterManager(){
        String oldpasswordText = account.getText();
        String newpasswordText1 = password1.getText();
        String newpasswordText2 = password2.getText();
        if(StringUtil.isEmpty(oldpasswordText)){
            errorinfo.setText("请输入账号!!");
            errorinfo.setVisible(true);
            return;
        }
        if(StringUtil.isEmpty(newpasswordText1)){
            errorinfo.setText("请输入新密码!!");
            errorinfo.setVisible(true);
            return;
        }
        if(StringUtil.isEmpty(newpasswordText2)){
            errorinfo.setText("请确认新密码!!");
            errorinfo.setVisible(true);
            return;
        }
        if(!newpasswordText1.equals(newpasswordText2)){
            errorinfo.setText("两次输入密码有误！！");
            errorinfo.setVisible(true);
            return;
        }else{
            Manager manager = new Manager(current.getAccount(), newpasswordText1);
            DBUtil.alter(manager);
            System.out.println("修改成功" + Main.getAccount());
        }
    }
}
