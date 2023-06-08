package com.huihe.demo.util;

import com.huihe.demo.Main;
import com.huihe.demo.entity.Book;
import com.huihe.demo.entity.Log;
import com.huihe.demo.entity.Manager;
import com.huihe.demo.entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {

    public static void add(Student student){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library", "root", "123456");){
            PreparedStatement statement = connection.prepareStatement("insert into students values(?,?)");
            statement.setString(1,student.getAccount());
            statement.setString(2,student.getPassword());
            statement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void add(Manager manager){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library", "root", "123456");){
            PreparedStatement statement = connection.prepareStatement("insert into managers values(?,?)");
            statement.setString(1,manager.getAccount());
            statement.setString(2,manager.getPassword());
            statement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void add(Log log){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library", "root", "123456");){
            PreparedStatement statement = connection.prepareStatement("insert into logs values(?,?,?,?,?)");
            statement.setString(1,log.getAccount());
            statement.setString(2,log.getIsbn());
            statement.setString(3,log.getName());
            statement.setString(4,log.getBorrowDate());
            statement.setString(5,log.getReturnDate());
            statement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void add(Book book){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library", "root", "123456");){
            PreparedStatement statement = connection.prepareStatement("insert into books values(?,?,?,?,?,?)");
            statement.setString(1,book.getIsbn());
            statement.setString(2,book.getName());
            statement.setString(3,book.getAuthor());
            statement.setString(4,book.getPublishDate());
            statement.setString(5,book.getPublisher());
            statement.setInt(6,book.getAvailable());
            statement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void alter(Student student){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library", "root", "123456");){
            PreparedStatement statement = connection.prepareStatement("UPDATE students SET password = ? WHERE account = ?");
            statement.setString(1,student.getPassword());
            statement.setString(2,student.getAccount());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void alter(Manager manager){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library", "root", "123456");){
            PreparedStatement statement = connection.prepareStatement("UPDATE managers SET password = ? WHERE account = ?");
            statement.setString(1,manager.getPassword());
            statement.setString(2,manager.getAccount());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static boolean selectStudent(String account, String password){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library", "root", "123456");){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM students WHERE account = ?");
            statement.setString(1,account);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                String password1 = resultSet.getString("password");
                return password1.equals(password);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean selectManager(String account, String password){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library", "root", "123456");){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM managers WHERE account = ?");
            statement.setString(1,account);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                String password1 = resultSet.getString("password");
                return password1.equals(password);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static List<Book> readData() {
        List<Book> result = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library", "root", "123456");) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM books;");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String isbn = resultSet.getString("isbn");
                String name = resultSet.getString("name");
                String author = resultSet.getString("author");
                String publishDate = resultSet.getString("publishDate");
                String publisher = resultSet.getString("publisher");
                Integer available = resultSet.getInt("available");

                Book book = new Book(isbn, name, author, publishDate, publisher, available);
                result.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<Book> readData(String searchText) {
        List<Book> result = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library", "root", "123456");) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM books WHERE isbn LIKE ? OR name LIKE ? OR author LIKE ? OR publishDate like ? OR publisher like ?;");
            statement.setString(1, "%" + searchText + "%");
            statement.setString(2, "%" + searchText + "%");
            statement.setString(3, "%" + searchText + "%");
            statement.setString(4, "%" + searchText + "%");
            statement.setString(5, "%" + searchText + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String isbn = resultSet.getString("isbn");
                String name = resultSet.getString("name");
                String author = resultSet.getString("author");
                String publishDate = resultSet.getString("publishDate");
                String publisher = resultSet.getString("publisher");
                Integer available = resultSet.getInt("available");

                Book book = new Book(isbn, name, author, publishDate, publisher, available);
                result.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<Log> readLog() {
        List<Log> result = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library", "root", "123456");) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM logs WHERE account = ?;");
            statement.setString(1, Main.getAccount());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String isbn = resultSet.getString("isbn");
                String name = resultSet.getString("name");
                String borrowDate = resultSet.getString("borrowDate");
                String returnDate = resultSet.getString("returnDate");


                Log log = new Log(Main.getAccount(),isbn, name, borrowDate,returnDate);
                result.add(log);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<Student> readStudent() {
        List<Student> result = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library", "root", "123456");) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM students;");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String account = resultSet.getString("account");
                String password = resultSet.getString("password");
                Student student = new Student(account, password);
                result.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static List<Manager> readManager() {
        List<Manager> result = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library", "root", "123456");) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM managers;");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String account = resultSet.getString("account");
                String password = resultSet.getString("password");
                Manager manager = new Manager(account, password);
                result.add(manager);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void updateBorrow(Book book) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library", "root", "123456");) {

            PreparedStatement statement = connection.prepareStatement("UPDATE books SET available = ? WHERE isbn  = ?");
            statement.setInt(1, book.getAvailable() - 1);
            statement.setString(2, book.getIsbn());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updateReturn(String isbn) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library", "root", "123456");) {
            PreparedStatement s = connection.prepareStatement("SELECT * FROM books WHERE isbn = ?");
            s.setString(1, isbn);
            ResultSet resultSet = s.executeQuery();
            while(resultSet.next()){
                int available = resultSet.getInt("available");
                PreparedStatement statement = connection.prepareStatement("UPDATE books SET available = ? WHERE isbn  = ?");
                statement.setInt(1, available + 1);
                statement.setString(2, isbn);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void update(Book book) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library", "root", "123456");) {
            PreparedStatement statement = connection.prepareStatement("UPDATE books SET name = ?, author = ?, publishDate = ?, publisher = ?, available = ? WHERE isbn = ?");
            statement.setString(1, book.getName());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getPublishDate());
            statement.setString(4, book.getPublisher());
            statement.setInt(5, book.getAvailable());
            statement.setString(6, book.getIsbn());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void delete(Log log) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library", "root", "123456");) {

            PreparedStatement statement = connection.prepareStatement("DELETE FROM logs WHERE isbn = ? AND account = ?;");
            statement.setString(1, log.getIsbn());
            statement.setString(2, Main.getAccount());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(Book book) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library", "root", "123456");) {

            PreparedStatement statement = connection.prepareStatement("DELETE FROM books WHERE isbn = ?");
            statement.setString(1, book.getIsbn());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void delete(Student student) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library", "root", "123456");) {

            PreparedStatement statement = connection.prepareStatement("DELETE FROM students WHERE account = ?");
            statement.setString(1, student.getAccount());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(Manager manager) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library", "root", "123456");) {

            PreparedStatement statement = connection.prepareStatement("DELETE FROM managers WHERE account = ?");
            statement.setString(1, manager.getAccount());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


