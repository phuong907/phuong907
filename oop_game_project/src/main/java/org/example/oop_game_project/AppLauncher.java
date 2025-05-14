package org.example.oop_game_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppLauncher extends Application {
    public static void main(String[] args) {
        // Thêm VM options programmatically (khuyến nghị dùng cấu hình IDE)
        System.setProperty("javafx.sdk.path", "C:\\Users\\lethe\\Downloads\\javafx-sdk-20.0.2\\lib");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("/login.fxml")); // Sửa đường dẫn
        primaryStage.setScene(new Scene(loginRoot));
        primaryStage.show();
    }
}
