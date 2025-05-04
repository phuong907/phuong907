package org.example.oop_game_project.Collisions;

import org.example.oop_game_project.Components.Enemy.Enemy1;
import org.example.oop_game_project.Components.Enemy.Enemy3;
import org.example.oop_game_project.Components.FlameComponent;
import org.example.oop_game_project.GameType;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.physics.CollisionHandler;
import javafx.util.Duration;

import static org.example.oop_game_project.Constants.Constant.ENEMY_SPEED;
import static org.example.oop_game_project.DynamicEntityState.State.*;
import static org.example.oop_game_project.GameType.*;
import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGL.getGameWorld;

public class FlameEnemy3Handler extends CollisionHandler {
    public FlameEnemy3Handler() {
        super(GameType.FLAME, GameType.ENEMY3);
    }

    @Override
    protected void onCollision(Entity flame, Entity enemy) {
    }

    private void onTransform(Entity parent) {
}
