package org.example.oop_game_project.UI;

import static com.almasb.fxgl.dsl.FXGL.addUINode;
import static com.almasb.fxgl.dsl.FXGL.getdp;
import static com.almasb.fxgl.dsl.FXGL.getip;
import static org.example.oop_game_project.Constants.Constant.UI_FONT_SIZE;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

// Utility class for creating and adding UI label components to the game scene
public class UIComponents {
    // Method to add an integer-bound label UI element to the game scene
    public static void addILabelUI(String varName, String title, double x, double y) {
        // Create a new label for displaying text
        Label text = new Label();

        // Configure label properties
        text.setTextFill(Color.BLACK);
        text.setFont(Font.font("Showcard Gothic", UI_FONT_SIZE));

        // Bind the label's text property to an integer game variable with a formatted title
        text.textProperty().bind(
                getip(varName).asString(title)
        );

        // Add the label to the game scene at the specified x, y coordinates
        addUINode(text, x, y);
    }

    // Method to add a double-bound label UI element to the game scene
    public static void addDLabelUI(String varName, String title, double x, double y) {
        // Create a new label for displaying text
        Label text = new Label();

        // Configure label properties
        text.setTextFill(Color.BLACK);
        text.setFont(Font.font("Showcard Gothic", UI_FONT_SIZE));

        // Bind the label's text property to a double game variable with a formatted title
        text.textProperty().bind(
                getdp(varName).asString(title)
        );

        // Add the label to the game scene at the specified x, y coordinates
        addUINode(text, x, y);
    }
}