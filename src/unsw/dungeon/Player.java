package unsw.dungeon;


import java.util.List;
import java.util.ArrayList;
/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Movement movement;
    private List<Entity> inventory;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y, true, true, false);
        this.movement = new Movement(dungeon, this);
        this.inventory = new ArrayList<>();
    }

    public void move(Direction d) {
        //moveBoulder(d);
        movement.moveInDirection(d);
    }

    @Override
    public void updateState() {
        List<Entity> entitiesOnPlayer = dungeon.getEntitiesOnTile(getX(), getY());
        for (Entity entity : entitiesOnPlayer) {
            if (entity.canPickup()) {
                pickup(entity);
                break;
            }
            // other cases here, like killing an enemy
        }
    }

    private void pickup(Entity entity) {
        Entity result = dungeon.requestEntity(this, entity);
        if (result != null) {
            if (result instanceof Key) {
                Key key = (Key) result;
                pickupKey(key);
            }
        }
    }

    private void pickupKey(Key key) {
        for (Entity entity : inventory) {
            if (entity instanceof Key) {
                Key ownedKey = (Key) entity;
                drop(ownedKey); // dont actually need to cast here but w/e
                break;
            }
        }
        inventory.add(key);
    }

    private void drop(Entity entity) {
        if (inventory.contains(entity)) {
            dungeon.dropEntity(entity, getX(), getY());
            inventory.remove(entity);
        }
    }

    public Key requestKey(int id) {
        for (Entity e : inventory) {
            if (e instanceof Key) {
                Key key = (Key) e;
                if (key.getId() == id) {
                    inventory.remove(e);
                    return key;
                }
            }
        }
        return null;
    }

    @Deprecated
    private void moveBoulder(Direction D) {
        try {
            List<Entity> entities = dungeon.getEntitiesOnTile(getAdjacentX(D), getAdjacentY(D));
            for (Entity e : entities) {
                if (e instanceof Boulder) {
                    Boulder b = (Boulder) e;
                    // only attempts to move it - if there is another obstruction in that direction,
                    // it will not move and then the player will not be able to move in that
                    // direction either
                    b.moveInDirection(D);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("cant push edge of map");
        }
    }
}
