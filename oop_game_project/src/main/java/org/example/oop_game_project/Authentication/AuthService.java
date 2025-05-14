package org.example.oop_game_project.Authentication;

import java.sql.*;

public class AuthService {
    // → chỉ để Java connect MySQL, không liên quan tới form login/register
    public static final String URL =
            "jdbc:mysql://localhost:3306/bomberman" +
                    "?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
    public static final String USER = "bomber_app";
    public static final String PASS = "StrongPassword123";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL Driver loaded");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Kiểm xem đã có username trong bảng users chưa
    private static boolean exists(String username) {
        String sql = "SELECT 1 FROM users WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return true;  // an toàn: nếu lỗi, giả sử đã tồn tại
        }
    }

    /** Lưu user mới vào bảng users */
    public static boolean register(String username, String password) {
        if (exists(username))
            return false;

        String sql = "INSERT INTO users(username, password) VALUES(?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password); // production: hash trước khi lưu
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /** Kiểm tra username/password trong bảng users */
    public static boolean login(String username, String password) {
        String sql = "SELECT 1 FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
