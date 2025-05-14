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
        if (flame.getComponent(FlameComponent.class).isActivation()
                && enemy.getComponent(Enemy3.class).getState() != DIE) {
            enemy.getComponent(Enemy3.class).setStateDie();
            getGameTimer().runOnceAfter(() -> {
                onTransform(enemy);
                enemy.removeFromWorld();
                set("enemies", getGameWorld().getGroup(ENEMY1,
                        ENEMY2, ENEMY3, ENEMY4, ENEMY5).getSize());
            }, Duration.seconds(2.4));
        }
    }

    private void onTransform(Entity parent) {
        Entity child1 = spawn("enemy1", new SpawnData(parent.getX(), parent.getY()));
        Entity child2 = spawn("enemy1", new SpawnData(parent.getX(), parent.getY()));

        if (parent.getComponent(Enemy3.class).getDx() == 0) {
            child1.getComponent(Enemy1.class).setState(UP);
            child1.getComponent(Enemy1.class).setDxDy(0, -ENEMY_SPEED);
            child2.getComponent(Enemy1.class).setState(DOWN);
            child2.getComponent(Enemy1.class).setDxDy(0, ENEMY_SPEED);
        } else if (parent.getComponent(Enemy3.class).getDy() == 0) {
            child1.getComponent(Enemy1.class).setState(LEFT);
            child1.getComponent(Enemy1.class).setDxDy(-ENEMY_SPEED, 0);
            child2.getComponent(Enemy1.class).setState(RIGHT);
            child2.getComponent(Enemy1.class).setDxDy(ENEMY_SPEED, 0);
        }
    }
}
