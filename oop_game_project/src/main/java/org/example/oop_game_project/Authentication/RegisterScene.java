package org.example.oop_game_project.Authentication;

import javafx.scene.control.*;

public class RegisterScene {
    private TextField usernameField;
    private PasswordField passwordField;
    private Button registerButton;

    public RegisterScene() {
        usernameField = new TextField();
        passwordField = new PasswordField();
        registerButton = new Button("Register");

        registerButton.setOnAction(e -> registerUser());
    }

    private void registerUser() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Call a method to insert user into the database
        DatabaseConnector.connect();  // Ensure you call your connect method if needed
        // Insert user logic here
    }
}
