package org.example.oop_game_project.Menu;

import org.example.oop_game_project.UI.StageStartScene;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.input.view.KeyView;
import javafx.geometry.Pos;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import static org.example.oop_game_project.Sounds.SoundEffect.setSoundSwitch;
import static org.example.oop_game_project.Sounds.SoundEffect.turnOffMusic;
import static com.almasb.fxgl.dsl.FXGL.*;
import static javafx.scene.input.KeyCode.*;

// Class representing the main menu for the game
public class MainMenu extends FXGLMenu {
    // Constructor for the main menu, initializing the menu UI
    public MainMenu() {
        // Initialize the menu as a MAIN_MENU type
        super(MenuType.MAIN_MENU);

        // Set up the background image for the menu
        ImageView background = new ImageView();
        background.setImage(new Image("assets/textures/main_background.png"));

        // Configure a drop shadow effect for the title text
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.rgb(185, 19, 21));
        dropShadow.setHeight(8);
        dropShadow.setWidth(8);
        dropShadow.setOffsetX(8);
        dropShadow.setOffsetY(10);
        dropShadow.setSpread(10);

        // Create and style the game title text
        var title = getUIFactoryService().newText(getSettings().getTitle(), Color.rgb(248, 185, 54), 130);
        title.setEffect(dropShadow);
        // Center the title text horizontally at y=300
        centerTextBind(title, getAppWidth() / 2.0, 300);

        // Create and style the version text
        var version = getUIFactoryService().newText(getSettings().getVersion(), Color.WHITE, 25);
        version.setEffect(new DropShadow(3, 3, 3, Color.RED));
        // Position the version text at x=860, y=250
        centerTextBind(version, 860, 250);

        // Create a vertical box to hold menu buttons
        var menuBox = new VBox(
                // Button to start a new game
                new MenuButton("New Game", 27, () -> newGame()),
                // Button to show game controls
                new MenuButton("Control", 27, () -> instruct()),
                // Button to toggle sound settings
                new MenuButton("Sound", 27, () -> setSoundSwitch()),
                // Button to exit the game
                new MenuButton("Exit", 27, () -> fireExit())
        );

        // Align menu buttons to the center-left and position the box
        menuBox.setAlignment(Pos.CENTER_LEFT);
        menuBox.setTranslateX(getAppWidth() * 0.35);
        menuBox.setTranslateY(getAppHeight() / 2.0 + 60);
        menuBox.setSpacing(20);

        // Add all UI elements to the menu's content root
        getContentRoot().getChildren().addAll(background, title, version, menuBox);
    }

    // Method to display a dialog box with game control instructions
    private void instruct() {
        // Create a grid pane to organize control instructions
        GridPane pane = new GridPane();

        // Add movement controls (W, S, A, D keys)
        pane.addRow(0, getUIFactoryService().newText(" Movement      "),
                new HBox(new KeyView(W), new KeyView(S), new KeyView(A), new KeyView(D)));
        // Add bomb placement control (SPACE key)
        pane.addRow(1, getUIFactoryService().newText(" Placed Bomb      "),
                new KeyView(SPACE));

        // Show the dialog box with the control instructions and an OK button
        getDialogService().showBox("How to Play", pane, getUIFactoryService().newButton("OK"));
    }

    // Method to start a new game
    public void newGame() {
        // Trigger the new game event
        fireNewGame();
        // Run a delayed task to turn off music and show the stage start scene
        getGameTimer().runOnceAfter(() -> {
            turnOffMusic();
            getSceneService().pushSubScene(new StageStartScene());
        }, Duration.millis(10));
    }
}