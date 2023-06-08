package com.huihe.demo.controller;

import com.huihe.demo.Main;
import javafx.fxml.FXML;

public class EntranceController {

    @FXML
    public void initialize(){

    }
    @FXML
    public void enterStudent(){
        Main.changeView("view/student-login.fxml");
    }

    @FXML
    public void enterManager(){
        Main.changeView("view/manager-login.fxml");
    }
}
