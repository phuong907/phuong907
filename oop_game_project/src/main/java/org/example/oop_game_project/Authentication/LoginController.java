package org.example.oop_game_project.Authentication;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label messageLabel;

    @FXML
    private void onLogin() {
        String user = usernameField.getText().trim();
        String pwd  = passwordField.getText().trim();

        if (user.isEmpty() || pwd.isEmpty()) {
            messageLabel.setText("Vui lòng nhập đầy đủ.");
            return;
        }

        if (org.example.oop_game_project.Authentication.AuthService.login(user, pwd)) {
            // Đóng cửa sổ login hiện tại
            Stage loginStage = (Stage) usernameField.getScene().getWindow();
            loginStage.close();

            // Lấy đường dẫn JavaFX SDK
            String sdkPath = System.getProperty("javafx.sdk.path");
            if (sdkPath == null || sdkPath.isEmpty()) {
                messageLabel.setText("Chưa cấu hình VM option: -Djavafx.sdk.path=đường_dẫn_đến_javafx_sdk/lib");
                return;
            }

            // Chuẩn bị ProcessBuilder để chạy GameApp$Launcher
            String javaBin = System.getProperty("java.home") + "/bin/java";
            String classpath = System.getProperty("java.class.path");

            ProcessBuilder pb = new ProcessBuilder(
                    javaBin,
                    "--module-path", sdkPath,
                    "--add-modules", "javafx.controls,javafx.fxml,javafx.media",
                    "--add-exports=javafx.graphics/com.sun.glass.utils=ALL-UNNAMED",
                    "--add-exports=javafx.media/com.sun.media.jfxmediaimpl=ALL-UNNAMED",
                    "-cp", classpath,
                    "org.example.oop_game_project.GameApp$Launcher"
            );

            pb.inheritIO();
            try {
                pb.start();
            } catch (IOException e) {
                e.printStackTrace();
                messageLabel.setText("Không thể khởi động game.");
            }

        } else {
            messageLabel.setText("Sai tên đăng nhập hoặc mật khẩu.\nChưa có tài khoản? Đăng ký ngay.");
        }
    }

    @FXML
    private void onShowRegister() {
        loadScene("/register.fxml", "Bomberman - Đăng Ký");
    }

    private void loadScene(String fxml, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
        } catch (Exception ex) {
            ex.printStackTrace();
            messageLabel.setText("Không thể mở trang " + title);
        }
    }
}
