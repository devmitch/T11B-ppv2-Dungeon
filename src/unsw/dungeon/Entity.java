package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.List;
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
    protected Dungeon dungeon;

    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public Entity(Dungeon dungeon, int x, int y, boolean isObstruction, boolean interactable, boolean canPickup) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.isObstruction = isObstruction;
        this.status = new SimpleBooleanProperty();
        this.status.setValue(true);
        this.interactable = interactable;
        this.canPickup = canPickup;
        this.dungeon = dungeon;
    }

    public void updateState() {
        
    }

    // another entity interacting with this one
    public void interactWith(Entity e, Direction D) {

    }

    // this entity interacting with the entities on some tile
    // problem: is it ok for an entity to interact with any tile on the map?
    protected void interactWithEntities(int x, int y, Direction D) {
        try {
            List<Entity> entities = dungeon.getEntitiesOnTile(x, y);
            for (Entity entity : entities) {
                if (entity.isInteractable()) {
                    entity.interactWith(this, D);
                }
            }
        } catch (Exception e) {
            // maybe coordinates are out of bounds?
        }
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
