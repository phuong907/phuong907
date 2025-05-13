package org.example.oop_game_project.UI;

import static com.almasb.fxgl.dsl.FXGL.animationBuilder;
import static com.almasb.fxgl.dsl.FXGL.getUIFactoryService;
import static com.almasb.fxgl.dsl.FXGL.geti;
import static com.almasb.fxgl.dsl.FXGL.play;
import static org.example.oop_game_project.Constants.Constant.SCENE_HEIGHT;
import static org.example.oop_game_project.Constants.Constant.SCENE_WIDTH;
import static org.example.oop_game_project.Sounds.SoundEffect.turnOnMusic;

import com.almasb.fxgl.scene.SubScene;
import javafx.scene.effect.Bloom;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

// Class representing the stage start scene displayed when a new game level begins
public class StageStartScene extends SubScene {
    // Constructor for the stage start scene, initializing the UI and animation
    public StageStartScene() {
        // Play the stage start sound effect
        play("stage_start.wav");

        // Initialize background
        var background = new Rectangle(
                SCENE_WIDTH,
                SCENE_HEIGHT,
                Color.color(0, 0, 0, 1)
        ); // Create a fully opaque black rectangle as the background

        // Initialize stage title
        var title = getUIFactoryService().newText(
                "Stage " + geti("level"),
                Color.WHITE,
                40
        ); // Create and style the stage title text, displaying the current level
        title.setStroke(Color.WHITESMOKE); // Add a light gray outline to the text
        title.setStrokeWidth(1.5); // Set the outline thickness
        title.setEffect(new Bloom(0.6)); // Apply a bloom effect for a glowing appearance
        title.setX(SCENE_WIDTH / 3); // Position the text horizontally
        title.setY(SCENE_HEIGHT / 2); // Center the text vertically

        // Add elements to the scene
        getContentRoot().getChildren().addAll(background, title); // Add the background and title to the scene's content root

        // Configure and play animation
        animationBuilder()
                .onFinished(() -> popSubScene()) // Call popSubScene when animation finishes
                .duration(Duration.seconds(4)) // Set animation duration to 4 seconds
                .fade(getContentRoot()) // Apply fade effect to the content root
                .from(1) // Start with full opacity
                .to(1) // Maintain full opacity
                .buildAndPlay(this); // Build and play the animation
    }

    // Method to pop the current subscene and resume background music
    public void popSubScene() {
        // Turn on the background music
        turnOnMusic();
        // Remove the current subscene from the scene stack
        getSceneService().popSubScene();
    }
}