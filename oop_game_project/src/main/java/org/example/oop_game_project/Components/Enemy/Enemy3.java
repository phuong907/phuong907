package org.example.oop_game_project.Components.Enemy;

import org.example.oop_game_project.Collisions.FlameEnemy3Handler;
import com.almasb.fxgl.physics.PhysicsWorld;

import static org.example.oop_game_project.Constants.Constant.ENEMY_SPEED;
import static org.example.oop_game_project.GameType.*;
import static com.almasb.fxgl.dsl.FXGL.*;

public class Enemy3 extends org.example.oop_game_project.Components.Enemy.Enemy {
    public Enemy3() {
        super(-ENEMY_SPEED, 0, 2,4, "enemy3.png");
        PhysicsWorld physics = getPhysicsWorld();

        onCollisionBegin(ENEMY3, BRICK, (enemy3, brick) -> {
            enemy3.getComponent(Enemy3.class).turn();
        });
        onCollisionBegin(ENEMY3, WALL, (enemy3, wall) -> {
            enemy3.getComponent(Enemy3.class).turn();
        });
        onCollisionBegin(ENEMY3, DOOR, (enemy3, door) -> {
            enemy3.getComponent(Enemy3.class).turn();
        });
        onCollisionBegin(ENEMY3, BOMB, (enemy3, bomb) -> {
            enemy3.getComponent(Enemy3.class).turn();
        });

        physics.addCollisionHandler(new FlameEnemy3Handler());
    }

}
