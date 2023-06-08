package com.huihe.demo.entity;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.CheckBox;

public class Book {
    private String isbn;
    private String name;
    private String author;
    private String publishDate;
    private String publisher;
    private Integer available;

    //private Boolean select;
    private BooleanProperty select;

    public boolean isSelected() {
        return select.get();
    }

    public void setSelected(boolean selected) {
        this.select.set(selected);
    }

    public BooleanProperty selectedProperty() {
        return select;
    }

    public Book() {
        //this.select = false;
        this.select = new SimpleBooleanProperty(false);
    }

    public Book(String isbn, String name, String author, String publishDate, String publisher, Integer available) {
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.publishDate = publishDate;
        this.publisher = publisher;
        this.available = available;
        this.select = new SimpleBooleanProperty(false);
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }
}
