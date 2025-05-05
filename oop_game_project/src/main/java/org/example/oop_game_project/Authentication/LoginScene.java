package org.example.oop_game_project.Authentication;

import com.almasb.fxgl.scene.SubScene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javax.swing.JOptionPane;

public class LoginScene extends SubScene {
    public LoginScene() {
        addLoginUI();
    }

    private void addLoginUI() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(20));

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");

        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(loginButton, 0, 2);
        grid.add(registerButton, 1, 2);

        loginButton.setOnAction(e -> {
            handleLogin(usernameField.getText(), passwordField.getText());
        });

        registerButton.setOnAction(e -> {
            getSceneService().pushSubScene(new RegisterScene());
        });

        getContentRoot().getChildren().add(grid);
    }

    private void handleLogin(String username, String password) {
        if (isInputValid(username, password)) {
            if (loginUser(username, password)) {
                System.out.println("Login successful!");
                getSceneService().popSubScene();
            } else {
                showAlert("Login failed!", "Invalid username or password.");
            }
        }
    }

    private boolean isInputValid(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Input Error", "Username and password cannot be empty.");
            return false;
        }
        return true;
    }

    private void showAlert(String title, String message) {
        // Hiển thị cảnh báo (có thể thay thế bằng cách thức hiển thị khác)
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
    }

    private boolean loginUser(String username, String password) {
        DatabaseConnector db = new DatabaseConnector();
        boolean isConnected = db.connect();
        if (isConnected) {
            return db.loginUser(username, password);
        }
        return false;
    }
}
