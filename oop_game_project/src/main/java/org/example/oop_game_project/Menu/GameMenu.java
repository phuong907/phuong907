package org.example.oop_game_project.Menu;

import static com.almasb.fxgl.dsl.FXGL.centerTextBind;
import static com.almasb.fxgl.dsl.FXGL.getUIFactoryService;
import static com.almasb.fxgl.dsl.FXGL.getSettings;
import static org.example.oop_game_project.Constants.Constant.SCENE_HEIGHT;
import static org.example.oop_game_project.Constants.Constant.SCENE_WIDTH;
import static org.example.oop_game_project.Sounds.SoundEffect.setSoundSwitch;

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

// Class representing the in-game pause menu for the game
public class GameMenu extends FXGLMenu {
    // Constructor for the game menu, initializing the pause menu UI
    public GameMenu() {
        // Initialize the menu as a GAME_MENU type
        super(MenuType.GAME_MENU);

        // Initialize background overlay
        Shape shape = new Rectangle(
                SCENE_WIDTH,
                SCENE_HEIGHT,
                Color.GREY
        ); // Create a semi-transparent gray rectangle as the background overlay
        shape.setOpacity(0.5);

        // Initialize background image
        ImageView background = new ImageView();
        background.setImage(new Image("assets/textures/esc_background.png"));
        background.setX(160);
        background.setY(90);
        // Apply drop shadow and lighting effects to the background image
        background.setEffect(new DropShadow(5, 3.5, 3.5, Color.WHITE));
        background.setEffect(new Lighting());

        // Configure title drop shadow
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.rgb(185, 19, 21));
        dropShadow.setHeight(7);
        dropShadow.setWidth(7);
        dropShadow.setOffsetX(6);
        dropShadow.setOffsetY(8);
        dropShadow.setSpread(8);

        // Initialize game title
        var title = getUIFactoryService().newText(
                getSettings().getTitle(),
                Color.rgb(248, 185, 54),
                90
        ); // Create and style the game title text
        title.setEffect(dropShadow);
        // Center the title text horizontally at y=310
        centerTextBind(title, getAppWidth() / 2.0, 310);

        // Initialize version text
        var version = getUIFactoryService().newText(
                getSettings().getVersion(),
                Color.WHITE,
                20
        ); // Create and style the version text
        version.setEffect(new DropShadow(3, 3, 3, Color.RED));
        // Position the version text at x=800, y=280
        centerTextBind(version, 800, 280);

        // Initialize menu buttons
        var menuBox = new VBox(
                // Button to resume the game
                new MenuButton("Resume", 20, () -> fireResume()),
                // Button to toggle sound settings
                new MenuButton("Sound", 20, () -> setSoundSwitch()),
                // Button to return to the main menu
                new MenuButton("Menu", 20, () -> fireExitToMainMenu()),
                // Button to exit the game
                new MenuButton("Exit", 20, () -> fireExit())
        ); // Create a vertical box to hold menu buttons

        // Configure menu box alignment and position
        menuBox.setAlignment(Pos.CENTER_LEFT);
        menuBox.setTranslateX(getAppWidth() / 2.0 - 110);
        menuBox.setTranslateY(getAppHeight() / 2.0 + 50);
        menuBox.setSpacing(20);

        // Add all UI elements to the menu
        getContentRoot().getChildren().addAll(
                shape,
                background,
                title,
                version,
                menuBox
        ); // Add all UI elements to the menu's content root
    }
}