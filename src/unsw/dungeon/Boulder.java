package unsw.dungeon;

public class Boulder extends Entity {

    private Dungeon dungeon;
    private Movement movement;

    public Boulder(Dungeon dungeon, int x, int y) {
        super(x, y, true, true, false);
        this.dungeon = dungeon;
        this.movement = new Movement(dungeon, this);
    }

    @Override
    public void interactWith(Entity e, Direction D) {
        if (e instanceof Player) {
            movement.moveInDirection(D);
        }
    }

    @Override
    public void updateState() {
        //kill enemies
    }
    /**
     * 
     * @param D
     */
    public void moveInDirection(Direction D) {
        movement.moveInDirection(D);
    }

}