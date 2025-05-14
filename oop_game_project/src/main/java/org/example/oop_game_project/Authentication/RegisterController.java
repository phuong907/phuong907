package org.example.oop_game_project.Authentication;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RegisterController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmField;
    @FXML private Label messageLabel;

    @FXML
    private void onRegister() {
        String user    = usernameField.getText().trim();
        String pwd     = passwordField.getText().trim();
        String confirm = confirmField.getText().trim();

        if (user.isEmpty() || pwd.isEmpty() || confirm.isEmpty()) {
            messageLabel.setText("Vui lòng điền đủ thông tin.");
            return;
        }
        if (!pwd.equals(confirm)) {
            messageLabel.setText("Mật khẩu không khớp.");
            return;
        }

        boolean ok = org.example.oop_game_project.Authentication.AuthService.register(user, pwd);
        if (ok) {
            messageLabel.setText("Đăng ký thành công! Quay về đăng nhập...");
            PauseTransition pt = new PauseTransition(Duration.seconds(1.2));
            pt.setOnFinished(e -> loadScene("/login.fxml", "Bomberman - Đăng Nhập"));
            pt.play();
        } else {
            messageLabel.setText("Tài khoản đã tồn tại hoặc lỗi hệ thống.");
        }
    }

    @FXML
    private void onBackToLogin() {
        loadScene("/login.fxml", "Bomberman - Đăng Nhập");
    }

    private void loadScene(String fxml, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();
            Stage st = (Stage) usernameField.getScene().getWindow();
            st.setTitle(title);
            st.setScene(new Scene(root));
        } catch (Exception ex) {
            ex.printStackTrace();
            messageLabel.setText("Không thể mở trang " + title);
        }
    }
}
