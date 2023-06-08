package com.huihe.demo.entity;

public class Student {

    private String account;

    private String password;

    public Student(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public Student() {
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
