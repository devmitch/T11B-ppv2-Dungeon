package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class Entity {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private BooleanProperty status;
    private IntegerProperty x, y;
    protected boolean isObstruction;
    protected boolean interactable;
    private boolean canPickup;

    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public Entity(int x, int y, boolean isObstruction, boolean interactable, boolean canPickup) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.isObstruction = isObstruction;
        this.status = new SimpleBooleanProperty();
        this.status.setValue(true);
        this.interactable = interactable;
        this.canPickup = canPickup;
    }

    public void updateState() {
        
    }

    public void interactWith(Entity e, Direction D) {

    }

    public boolean isInteractable() {
        return this.interactable;
    }

    public boolean canPickup() {
        return this.canPickup;
    }

    public void delete() {
        this.status.setValue(false);
    }

    public boolean isObstruction() {
        return isObstruction;
    }

    public BooleanProperty status() {
        return status;
    }

    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }

    public void setY(int y) {
        this.y.set(y);
    }

    public void setX(int x) {
        this.x.set(x);
    }

    /**
     * 
     * @param D
     * @return
     */
    public int getAdjacentX(Direction D) {
        switch(D) {
            case LEFT:
                return getX() - 1;
            case RIGHT:
                return getX() + 1;
            default:
                return getX();
        }
    }

    /**
     * 
     * @param D
     * @return
     */
    public int getAdjacentY(Direction D) {
        switch(D) {
            case UP:
                return getY() - 1;
            case DOWN:
                return getY() + 1;
            default:
                return getY();
        }
    }
}
