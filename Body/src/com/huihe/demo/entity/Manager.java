package com.huihe.demo.entity;

public class Manager {
    private String account;

    private String password;

    public Manager(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public Manager() {
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
