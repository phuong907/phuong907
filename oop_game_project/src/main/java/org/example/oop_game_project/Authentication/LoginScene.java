package org.example.oop_game_project.Authentication;

import javafx.scene.control.*;

public class LoginScene {
    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;

    public LoginScene() {
        usernameField = new TextField();
        passwordField = new PasswordField();
        loginButton = new Button("Login");

        loginButton.setOnAction(e -> loginUser());
    }

    private void loginUser() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Call a method to verify user credentials
        DatabaseConnector.connect();  // Ensure you call your connect method as needed
        // Verification logic here
    }
}
