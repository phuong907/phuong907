package org.example.oop_game_project.Authentication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnector {
    private Connection connection;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/game_db";
    private static final String DB_USERNAME = "your_username"; // Thay đổi thành username của bạn
    private static final String DB_PASSWORD = "your_password"; // Thay đổi thành password của bạn

    public boolean connect() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Connected to database successfully!");
            return true;
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
            return false;
        }
    }

    public boolean registerUser(String username, String password) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO users (username, password) VALUES (?, ?)");
            statement.setString(1, username);
            statement.setString(2, password);
            statement.executeUpdate();
            System.out.println("User registered successfully!");
            return true;
        } catch (SQLException e) {
            System.out.println("Error registering user: " + e.getMessage());
            return false;
        }
    }

    public boolean loginUser(String username, String password) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM users WHERE username = ? AND password = ?");
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println("Error logging in user: " + e.getMessage());
            return false;
        }
    }

    public void disconnect() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Disconnected from database!");
            }
        } catch (SQLException e) {
            System.out.println("Error disconnecting from database: " + e.getMessage());
        }
    }
}


