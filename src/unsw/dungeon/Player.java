package unsw.dungeon;


import java.util.List;
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
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y, true);
        this.dungeon = dungeon;
        this.movement = new Movement(dungeon, this);
    }

    public void moveUp() {
        moveBoulder(Direction.UP);
        movement.moveUp();
    }

    public void moveDown() {
        moveBoulder(Direction.DOWN);
        movement.moveDown();
    }

    public void moveLeft() {
        moveBoulder(Direction.LEFT);
        movement.moveLeft();
    }

    public void moveRight() {
        moveBoulder(Direction.RIGHT);
        movement.moveRight();
    }

    public void moveBoulder(Direction D) {
        List<Entity> entities = dungeon.getEntitiesOnTile(getAdjacentX(D), getAdjacentY(D));
        for (Entity e : entities) {
            if (e instanceof Boulder) {
                Boulder b = (Boulder) e;
                // only attempts to move it - if there is another obstruction in that direction,
                // it will not move and then the player will not be able to move in that
                // direction either
                b.move(D);
            }
        }
    }
}
