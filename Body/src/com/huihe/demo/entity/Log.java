package com.huihe.demo.entity;

public class Log {
    private String account;
    private String isbn;
    private String name;
    private String borrowDate;
    private String returnDate;

    public Log(String account, String isbn, String name, String borrowDate, String returnDate) {
        this.account = account;
        this.isbn = isbn;
        this.name = name;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public Log() {
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}
