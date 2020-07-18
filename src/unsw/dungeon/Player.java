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
        movement.moveInDirection(d);
        dungeon.updateEnemies();
        stepTaken();
    }

    @Override
    public void interactWith(Entity e, Direction D) {
        if (e instanceof Enemy) {
            duel((Enemy)e);
        }
    }

    private void stepTaken() {
        if (potion != null) {
            if (!potion.isInvincible()) {
                potion = null;
            } else {
                potion.decrementNumberOfSteps();
            }
        }
    }

    public boolean isInvincible() {
        if (potion != null)
            return potion.isInvincible();
        return false;
    }

    public void duel(Enemy enemy) {
        boolean swordSwung = false;
        if (this.sword != null) {
            swordSwung = sword.attemptSwing();
        }
        if (potion != null && potion.isInvincible()) {
            dungeon.removeEntity(enemy);
        } else if (swordSwung) {
            dungeon.removeEntity(enemy);
            System.out.print("Hits left: ");
            System.out.println(sword.getDurability());
            if (sword.getDurability() == 0) {
                this.sword = null;
            }
            System.out.println("You won!");
        } else {
            System.out.println("You lost!");
        }
    }

    public void moveToEntity(Entity e) {
        movement.moveToEntity(e);
    }

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
            dungeon.dropEntity(key, getX(), getY());
        }
        this.key = key;
    }

    public Key requestKey(int id) {
        if (this.key != null && this.key.getId() == id) {
            Key ret = this.key;
            this.key = null;
            return ret;
        } else {
            return null;
        }
    }
}
