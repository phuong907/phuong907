package org.example.oop_game_project.Menu;

import com.almasb.fxgl.dsl.FXGL;
import javafx.beans.binding.Bindings;
import javafx.scene.Parent;
import javafx.scene.paint.Color;

// Class representing a customizable menu button with hover effects
public class MenuButton extends Parent {
    // Constructor for the menu button, initializing text and behavior
    MenuButton(String name, double fontSize, Runnable action) {
        // Create a text node for the button label with specified name, color, and font size
        var text = FXGL.getUIFactoryService().newText(name, Color.WHITE, fontSize);
        // Set the stroke width for the text outline
        text.setStrokeWidth(1.5);
        // Bind the stroke color to match the text fill color
        text.strokeProperty().bind(text.fillProperty());

        // Bind the text fill color to change based on hover state
        text.fillProperty().bind(
                Bindings.when(hoverProperty())
                        .then(Color.rgb(248, 185, 54)) // Yellow-orange color when hovered
                        .otherwise(Color.WHITE)        // White color when not hovered
        );

        // Set the action to run when the button is clicked
        setOnMouseClicked(e -> action.run());

        // Enable mouse event detection for the button's bounds
        setPickOnBounds(true);
        // Add the text node to the button's children
        getChildren().add(text);
    }
}
