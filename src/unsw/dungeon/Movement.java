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

    private boolean canMove(int x, int y) {
        if (x < 0 || dungeon.getWidth() - 1 < x) {
            return false;
        } else if (y < 0 || dungeon.getHeight() - 1 < y) {
            return false;
        }

        for (Entity entity : dungeon.getEntitiesOnTile(x, y)) {
            if (entity.isObstruction()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 
     * @param x
     * @param y
     */
    private void move(int x, int y) {
        if (canMove(entity.getX() + x, entity.getY() + y))
            dungeon.moveEntity(entity, entity.getX() + x, entity.getY() + y);
    }

    /**
     * 
     * @param D
     */
    public void moveInDirection(Direction D) {
        switch(D) {
            case LEFT:
                move(-1, 0);
                break;
            case RIGHT:
                move(1, 0);
                break;
            case UP:
                move(0, -1);
                break;
            case DOWN:
                move(0, 1);
                break;
        }
    }

}