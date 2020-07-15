package unsw.dungeon;

import java.util.List;

import javafx.beans.property.IntegerProperty;

/**
 * Contains the functionality for moving an entity to a new tile.
 */
public class Movement {
    
    private Dungeon dungeon;
    private IntegerProperty x;
    private IntegerProperty y;

    public Movement(Dungeon dungeon, IntegerProperty x, IntegerProperty y) {
        this.dungeon = dungeon;
        this.x = x;
        this.y = y;
    }

    private boolean canMove(int x, int y) {
        List<Entity> entities = dungeon.getEntitiesOnTile(x, y);
        for (Entity entity : entities) {
            if (entity.getX() == x && entity.getY() == y) {
                return false;
            }
        }
        return true;
    }

    public void moveUp() {
        if (y.get() > 0 && canMove(x.get(), y.get() - 1))
            y.set(y.get() - 1);
    }

    public void moveDown() {
        if (y.get() < dungeon.getHeight() - 1 && canMove(x.get(), y.get() + 1))
            y.set(y.get() + 1);
    }

    public void moveLeft() {
        if (x.get() > 0 && canMove(x.get() - 1, y.get()))
            x.set(x.get() - 1);
    }

    public void moveRight() {
        if (x.get()< dungeon.getWidth() - 1 && canMove(x.get() + 1, y.get()))
            x.set(x.get() + 1);
    }

}