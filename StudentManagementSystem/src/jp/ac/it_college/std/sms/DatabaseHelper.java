package jp.ac.it_college.std.sms;

import java.sql.*;

public class DatabaseHelper {
    private static DatabaseHelper instance;
    private static final String driverName = "com.mysql.jdbc.Driver";
    private static final String host = "jdbc:mysql://localhost/sms";
    private static final String user = "user";
    private static final String password = "password";
    private Connection connection;

    public static DatabaseHelper getInstance() {
        if (instance == null) {
            instance = new DatabaseHelper();
        }
        return instance;
    }

    public DatabaseHelper() {
        try {
            Class.forName(driverName).newInstance();
            connection = DriverManager.getConnection(host, user, password);
            connection.setAutoCommit(false);
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException e) {
            System.err.println("ドライバーロード失敗");
        } catch (SQLException e) {
            System.err.println("データベースに接続できませんでした");
        }
    }

    public Statement getStatement() {
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    public PreparedStatement getPreparedStatement(String sql) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
