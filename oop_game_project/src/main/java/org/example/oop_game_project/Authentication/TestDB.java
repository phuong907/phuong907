package org.example.oop_game_project.Authentication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDB {
    public static void main(String[] args) {
        System.out.println("URL = " + org.example.oop_game_project.Authentication.AuthService.URL);
        System.out.println("USER = " + org.example.oop_game_project.Authentication.AuthService.USER);

        try {
            Connection conn = DriverManager.getConnection(
                    org.example.oop_game_project.Authentication.AuthService.URL,
                    org.example.oop_game_project.Authentication.AuthService.USER,
                    org.example.oop_game_project.Authentication.AuthService.PASS
            );
            System.out.println("✅ Kết nối thành công!");
            conn.close();
        } catch (SQLException e) {
            System.err.println("❌ Kết nối thất bại:");
            e.printStackTrace();
        }
    }
}
