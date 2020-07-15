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
        if (0 < x || x > dungeon.getWidth() - 1) {
            return false;
        } else if (0 < y || y > dungeon.getHeight() - 1) {
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

}