package com.huihe.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application{
    private static Stage stage;
    public static String account;

    public static String getAccount() {
        return account;
    }

    public static void setAccount(String account) {
        Main.account = account;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        Main.stage = stage;
        stage.setTitle("图书借阅管理系统");
        changeView("view/entrance.fxml");
        stage.show();
    }

    public static void changeView(String fxml){
        Parent root = null;
        try{
            root = FXMLLoader.load(Main.class.getResource(fxml));
            stage.setScene(new Scene(root));
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static void addView(String fxml){
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(Main.class.getResource(fxml));
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.show();
    }

}
