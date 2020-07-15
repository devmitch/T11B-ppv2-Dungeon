package unsw.dungeon;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;
    private Movement movement;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y, boolean isObstruction) {
        super(x, y, isObstruction);
        this.dungeon = dungeon;
        this.movement = new Movement(dungeon, this.x(), this.y());
    }

    public void moveUp() {
        movement.moveUp();
    }

    public void moveDown() {
        movement.moveDown();
    }

    public void moveLeft() {
        movement.moveLeft();
    }

    public void moveRight() {
        movement.moveRight();
    }
}
