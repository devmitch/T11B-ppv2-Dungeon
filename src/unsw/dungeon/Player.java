package unsw.dungeon;


import java.util.List;
/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Movement movement;
    private Key key;
    private Sword sword;
    private InvincibilityPotion potion;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y, true, true, false);
        this.movement = new Movement(dungeon, this);
        this.key = null;
        this.sword = null;
        this.potion = null;
    }

    public void move(Direction d) {
        movement.moveInDirection(d); // moves player
        dungeon.updateObservers(); // updates enemies
        stepTaken(); // updates potion steps left
    }

    // Duel enemies if the enemy interacts with the player
    @Override
    public void interactWith(Entity e, Direction D) {
        if (e instanceof Enemy) {
            duel((Enemy)e);
        }
    }

    // Decrement potion ticker if movement was attempted
    private void stepTaken() {
        if (potion != null && potion.isActive()) {
            potion.decrementNumberOfSteps();
        }
    }

    public boolean isInvincible() {
        if (potion != null)
            return potion.isActive();
        return false;
    }

    /**
     * Duel an enemy and decide the player's fate depending on their items
     * @param enemy
     */
    public void duel(Enemy enemy) {
        if (this.potion != null && this.potion.isActive()) {
            // win by potion
            enemy.die();
        } else if (this.sword != null && !this.sword.isBroken()) {
            // win by sword - decrement hits
            this.sword.swing();
            enemy.die();
        } else {
            // loss
            this.dungeon.removeEntity(this);
        }
    }

    public void moveToEntity(Entity e) {
        movement.moveToEntity(e);
    }

    // Check if we can pick up items
    @Override
    public void updateState() {
        List<Entity> entitiesOnPlayer = dungeon.getEntitiesOnTile(getX(), getY());
        for (Entity entity : entitiesOnPlayer) {
            if (entity.canPickup()) {
                entity.updateState();
                pickup(entity);
                break;
            }
        }
    }

    /**
     * Pick up certain entities depending on their type
     * @param entity
     */
    private void pickup(Entity entity) {
        Entity result = dungeon.requestEntity(this, entity); //this deletes the entity from the dungeon entity list
        if (result != null) {
            if (result instanceof Key) {
                Key key = (Key) result;
                pickupKey(key);
            } else if (result instanceof Sword) {
                Sword sword = (Sword) result;
                pickupSword(sword);
            } else if (result instanceof InvincibilityPotion) {
                InvincibilityPotion potion = (InvincibilityPotion) result;
                pickupPotion(potion);
            }
        }
    }

    private void pickupPotion(InvincibilityPotion potion) {
        this.potion = potion;
    }

    private void pickupSword(Sword sword) {
        this.sword = sword;
    }

    private void pickupKey(Key key) {
        if (this.key != null) {
            // swap keys
            dungeon.dropEntity(this.key, getX(), getY());
        }
        this.key = key;
    }

    // for testing
    public Key getKey() {
        return this.key;
    }

    public Sword getSword() {
        return this.sword;
    }

    public InvincibilityPotion getPotion() {
        return this.potion;
    }

    /**
     * Other entity (specifically door) requesting a key from the player
     * @param id Key id
     * @return
     */
    public Key useKey(int id) {
        if (this.key != null && this.key.getId() == id) {
            Key ret = this.key;
            this.key = null;
            return ret;
        } else {
            return null;
        }
    }
}
