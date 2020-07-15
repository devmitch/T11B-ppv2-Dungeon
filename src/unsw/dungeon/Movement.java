package unsw.dungeon;

import java.util.List;

import javafx.beans.property.IntegerProperty;

/**
 * Contains the functionality for moving an entity to a new tile.
 */
public class Movement {
    
    private Dungeon dungeon;
    private Entity entity;

    public Movement(Dungeon dungeon, Entity entity) {
        this.dungeon = dungeon;
        this.entity = entity;
    }

    private boolean canMove(int x, int y) {
        if (x < 0 || dungeon.getWidth() - 1 < x) {
            return false;
        } else if (y < 0 || dungeon.getHeight() - 1 < y) {
            return false;
        }

        for (Entity entity : dungeon.getEntitiesOnTile(x, y)) {
            if (entity.isObstruction()) {
                return false;
            }
        }
        return true;
    }

    public void moveUp() {
        if (canMove(entity.getX(), entity.getY() - 1))
            dungeon.moveEntity(entity, entity.getX(), entity.getY() - 1);
    }

    public void moveDown() {
        if (canMove(entity.getX(), entity.getY() + 1))
            dungeon.moveEntity(entity, entity.getX(), entity.getY() + 1);
    }

    public void moveLeft() {
        if (canMove(entity.getX() - 1, entity.getY()))
            dungeon.moveEntity(entity, entity.getX() - 1, entity.getY());
    }

    public void moveRight() {
        if (canMove(entity.getX() + 1, entity.getY()))
            dungeon.moveEntity(entity, entity.getX() + 1, entity.getY());
    }

    // generic movement in some direction
    public void moveInDirection(Direction D) {
        // use switch-case?
        if (D == Direction.LEFT) {
            moveLeft();
        } else if (D == Direction.RIGHT) {
            moveRight();
        } else if (D == Direction.UP) {
            moveUp();
        } else {
            moveDown();
        }
    }

}