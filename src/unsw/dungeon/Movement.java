package unsw.dungeon;

/**
 * Contains the functionality for moving an entity to a new tile.
 */
public class Movement {
    
    private Dungeon dungeon;
    private Entity entity;

    public Movement(Dungeon dungeon, Entity entity) {
        this.dungeon = dungeon;
        this.entity = entity;
    }

    /**
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @return true if the entity can move to the tile at (x, y), false otherwise.
     */
    private boolean canMove(int x, int y) {
        if (x < 0 || dungeon.getWidth() - 1 < x) {
            return false;
        } else if (y < 0 || dungeon.getHeight() - 1 < y) {
            return false;
        }

        return !dungeon.isTileObstructed(x, y);
    }

    /**
     * Attempts to move the entity in the given direction, updates the entities that are triggered
     * by any movement. 
     * @param x the x offset.
     * @param y the y offset.
     */
    private void move(int x, int y, Direction D) {
        this.entity.interactWithEntities(entity.getX() + x, entity.getY() + y, D);
        if (!dungeon.getEntitiesOnTile(entity.getX(), entity.getY()).contains(this.entity)) {
            return; //entity was deleted in interaction
        }
        if (canMove(entity.getX() + x, entity.getY() + y))
            dungeon.moveEntity(entity, entity.getX() + x, entity.getY() + y);
        this.entity.updateState();
    }

    /**
     * Attempts to move the entity in the given direction.
     * 
     * @param D the direction of movement.
     */
    public void moveInDirection(Direction D) {
        switch(D) {
            case LEFT:
                move(-1, 0, D);
                break;
            case RIGHT:
                move(1, 0, D);
                break;
            case UP:
                move(0, -1, D);
                break;
            case DOWN:
                move(0, 1, D);
                break;
            case NONE:
                break;
        }
    }

    /**
     * Moves the entity to the given entity e.
     * 
     * @param e the entity to move to.
     */
    public void moveToEntity(Entity e) {
        dungeon.moveEntity(entity, e.getX(), e.getY());
    }

}