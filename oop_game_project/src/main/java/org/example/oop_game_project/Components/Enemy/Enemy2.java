package org.example.oop_game_project.Components.Enemy;

import org.example.oop_game_project.Components.FlameComponent;
import org.example.oop_game_project.Components.PlayerComponent;
import com.almasb.fxgl.entity.Entity;
import javafx.util.Duration;

import static org.example.oop_game_project.Constants.Constant.ENEMY_SPEED;
import static org.example.oop_game_project.Constants.Constant.TILED_SIZE;
import static org.example.oop_game_project.DynamicEntityState.State.DIE;
import static org.example.oop_game_project.GameType.*;
import static com.almasb.fxgl.dsl.FXGL.*;

public class Enemy2 extends org.example.oop_game_project.Components.Enemy.Enemy {
    private boolean isCatching;

    public Enemy2() {
        super(-ENEMY_SPEED, 0, 1, 3, "enemy2.png");
        isCatching = true;
        onCollisionBegin(ENEMY2, BRICK, (enemy2, brick) -> {
            enemy2.getComponent(Enemy2.class).turn();
        });
        onCollisionBegin(ENEMY2, WALL, (enemy2, wall) -> {
            enemy2.getComponent(Enemy2.class).turn();
        });
        onCollisionBegin(ENEMY2, DOOR, (enemy2, door) -> {
            enemy2.getComponent(Enemy2.class).turn();
        });
        onCollisionBegin(ENEMY2, BOMB, (enemy2, bomb) -> {
            enemy2.getComponent(Enemy2.class).turn();
        });
        onCollision(ENEMY2, FLAME, (enemy2, flame) -> {
            if(flame.getComponent(FlameComponent.class).isActivation()) {
                enemy2.getComponent(Enemy2.class).setStateDie();
                getGameTimer().runOnceAfter(() -> {
                    enemy2.removeFromWorld();
                    set("enemies", getGameWorld().getGroup(ENEMY1,
                            ENEMY2, ENEMY3, ENEMY4, ENEMY5).getSize());
                }, Duration.seconds(2.4));
            }
        });

    }

    @Override
    public void onUpdate(double tpf) {
        super.onUpdate(tpf);

        Entity player = getGameWorld().getSingleton(PLAYER);

        if (state == DIE || player
                .getComponent(PlayerComponent.class)
                .getState() == DIE) {
            return;
        }

        int playerCellX = (int) (player.getX() / TILED_SIZE);
        int playerCellY = (int) (player.getY() / TILED_SIZE);
        int enemyCellY = (int) (entity.getY() / TILED_SIZE);
        int enemyCellX = (int) (entity.getX() / TILED_SIZE);
        if (getEntity().distance(player) < TILED_SIZE * 3) {
            if (isCatching == true) {
                if (dx == 0) {
                    if ((entity.getY() - player.getY()) * dy < 0) {
                        speedFactor = 1.3;
                    } else {
                        speedFactor = 1;
                    }

                    if (enemyCellY == playerCellY) {
                        if (player.getX() > entity.getX()) {
                            turnRight();
                        } else {
                            turnLeft();
                        }
                    }
                } else if (dy == 0) {
                    if ((entity.getX() - player.getX()) * dx < 0) {
                        speedFactor = 1.3;
                    } else {
                        speedFactor = 1;
                    }

                    if (enemyCellX == playerCellX) {
                        if (player.getY() > entity.getY()) {
                            turnDown();
                        } else {
                            turnUp();
                        }
                    }
                }
            } else if (dx == 0 && ((int) entity.getY() % TILED_SIZE <= 5 && (int) entity.getY() % TILED_SIZE > 0)) {
                isCatching = true;
            } else if (dy == 0 && ((int) entity.getX() % TILED_SIZE <= 5 && (int) entity.getY() % TILED_SIZE > 0)) {
                isCatching = true;
            }
        } else {
            speedFactor = 1;
            isCatching = true;
        }

    }

    @Override
    public void turn() {
        isCatching = false;
        super.turn();
    }
}
