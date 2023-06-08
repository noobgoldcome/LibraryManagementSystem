package com.huihe.demo.controller;

import com.huihe.demo.Main;
import com.huihe.demo.entity.Student;
import com.huihe.demo.util.DBUtil;
import com.huihe.demo.util.StringUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class StudentAlterPasswordController {
    @FXML
    private TextField oldpassword;
    @FXML
    private PasswordField newpassword1;
    @FXML
    private PasswordField newpassword2;
    @FXML
    private Label errorinfo;

    @FXML
    public void initialize(){

    }

    public void alterStudent(){
        String oldpasswordText = oldpassword.getText();
        String newpasswordText1 = newpassword1.getText();
        String newpasswordText2 = newpassword2.getText();
        if(StringUtil.isEmpty(oldpasswordText)){
            errorinfo.setText("请输入原密码!!");
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
            Student student = new Student(Main.getAccount(), newpasswordText1);
            DBUtil.alter(student);
            System.out.println("修改成功" + Main.getAccount());
        }
    }
}
