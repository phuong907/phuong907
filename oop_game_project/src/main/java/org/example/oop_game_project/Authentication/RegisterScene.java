package org.example.oop_game_project.Authentication;

import com.almasb.fxgl.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;

public class RegisterScene extends SubScene {

    private TextField usernameField;
    private TextField passwordField;
    private TextField confirmPasswordField;

    public RegisterScene() {
        super();
        createUI();
    }

    private void createUI() {
        GridPane grid = createGridPane();

        Label usernameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");
        Label confirmPasswordLabel = new Label("Confirm Password:");

        usernameField = new TextField();
        passwordField = new TextField();
        confirmPasswordField = new TextField();

        Button registerButton = new Button("Register");
        Button backButton = new Button("Back");

        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(confirmPasswordLabel, 0, 2);
        grid.add(confirmPasswordField, 1, 2);
        grid.add(registerButton, 0, 3);
        grid.add(backButton, 1, 3);

        // Action for register button
        registerButton.setOnAction(e -> handleRegister(usernameField.getText(), passwordField.getText(), confirmPasswordField.getText()));

        // Action for back button
        backButton.setOnAction(e -> this.setVisible(false));

        getRoot().getChildren().add(grid);
    }

    private void handleRegister(String username, String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            // Handle password mismatch (show error message, etc.)
            return;
        }
        // Here add your registration logic (e.g., save to database)
    }

    private GridPane createGridPane() {
        return new GridPane();
    }
}