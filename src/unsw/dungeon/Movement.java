package unsw.dungeon;

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

    public void moveUp() {
        if (y.get() > 0)
            y.set(y.get() - 1);
    }

    public void moveDown() {
        if (y.get() < dungeon.getHeight() - 1)
            y.set(y.get() + 1);
    }

    public void moveLeft() {
        if (x.get() > 0)
            x.set(x.get() - 1);
    }

    public void moveRight() {
        if (x.get()< dungeon.getWidth() - 1)
            x.set(x.get() + 1);
    }

}