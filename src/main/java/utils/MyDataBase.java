
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDataBase {
    private static MyDataBase instance;
    private Connection connection;

    // Database configuration
<<<<<<< HEAD
    private final String URL = "jdbc:mysql://localhost:3306/pidev3";
=======
    private final String URL = "jdbc:mysql://localhost:3306/bdd";
>>>>>>> 90781c80dac62ee0115c83aef63989fd27e397da
    private final String USER = "root";
    private final String PASSWORD = "";

    private MyDataBase() {
        try {
            // 1. Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2. Establish connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to database");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Connection error: " + e.getMessage());
        }
    }

    public static MyDataBase getInstance() {
        if (instance == null) {
            instance = new MyDataBase();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}


