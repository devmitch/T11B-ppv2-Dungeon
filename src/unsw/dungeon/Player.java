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

    public void moveToEntity(Entity e) {
        movement.moveToEntity(e);
    }

    @Override
    public void updateState() {
        List<Entity> entitiesOnPlayer = dungeon.getEntitiesOnTile(getX(), getY());
        for (Entity entity : entitiesOnPlayer) {
            if (entity.canPickup()) {
                pickup(entity);
            }
            // other cases here, like killing an enemy
        }
    }

    private void pickup(Entity entity) {
        Entity result = dungeon.requestEntity(this, entity);
        if (result != null) {
            inventory.add(result);
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
