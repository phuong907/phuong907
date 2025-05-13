package org.example.oop_game_project.Menu;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import javafx.geometry.Pos;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import static org.example.oop_game_project.Constants.Constant.*;
import static org.example.oop_game_project.Sounds.SoundEffect.setSoundSwitch;
import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGL.centerTextBind;

// Class representing the in-game pause menu for the game
public class GameMenu extends FXGLMenu {
    // Constructor for the game menu, initializing the pause menu UI
    public GameMenu() {
        // Initialize the menu as a GAME_MENU type
        super(MenuType.GAME_MENU);

        // Create a semi-transparent gray rectangle as the background overlay
        Shape shape = new Rectangle(SCENE_WIDTH, SCENE_HEIGHT, Color.GREY);
        shape.setOpacity(0.5);

        // Set up the background image for the menu
        ImageView background = new ImageView();
        background.setImage(new Image("assets/textures/esc_background.png"));
        background.setX(160);
        background.setY(90);
        // Apply drop shadow and lighting effects to the background image
        background.setEffect(new DropShadow(5, 3.5, 3.5, Color.WHITE));
        background.setEffect(new Lighting());

        // Configure a drop shadow effect for the title text
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.rgb(185, 19, 21));
        dropShadow.setHeight(7);
        dropShadow.setWidth(7);
        dropShadow.setOffsetX(6);
        dropShadow.setOffsetY(8);
        dropShadow.setSpread(8);

        // Create and style the game title text
        var title = getUIFactoryService().newText(getSettings().getTitle(), Color.rgb(248, 185, 54), 90);
        title.setEffect(dropShadow);
        // Center the title text horizontally at y=310
        centerTextBind(title, getAppWidth() / 2.0, 310);

        // Create and style the version text
        var version = getUIFactoryService().newText(getSettings().getVersion(), Color.WHITE, 20);
        version.setEffect(new DropShadow(3, 3, 3, Color.RED));
        // Position the version text at x=800, y=280
        centerTextBind(version, 800, 280);

        // Create a vertical box to hold menu buttons
        var menuBox = new VBox(
                // Button to resume the game
                new MenuButton("Resume", 20, () -> fireResume()),
                // Button to toggle sound settings
                new MenuButton("Sound", 20, () -> setSoundSwitch()),
                // Button to return to the main menu
                new MenuButton("Menu", 20, () -> fireExitToMainMenu()),
                // Button to exit the game
                new MenuButton("Exit", 20, () -> fireExit())
        );

        // Align menu buttons to the center-left and position the box
        menuBox.setAlignment(Pos.CENTER_LEFT);
        menuBox.setTranslateX(getAppWidth() / 2.0 - 110);
        menuBox.setTranslateY(getAppHeight() / 2.0 + 50);
        menuBox.setSpacing(20);

        // Add all UI elements to the menu's content root
        getContentRoot().getChildren().addAll(shape, background, title, version, menuBox);
    }
}