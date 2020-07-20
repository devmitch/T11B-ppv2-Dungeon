package unsw.dungeon;

public class Exit extends Entity {

    private ExitGoalType goal;

    public Exit(Dungeon dungeon, int x, int y, ExitGoalType goal) {
        super(dungeon, x, y, false, true, false);
        this.goal = goal;
    }
    
    @Override
    public void interactWith(Entity e, Direction d) {
        if (e instanceof Boulder) {
            this.dungeon.removeEntity(e);
        }
    }
    
    /**
     * Checks if the player and exit still occupy the same tile, updates the goal to represent the
     * state.
     */
    public void updateAtExitState() {
        if (dungeon.areEntitiesOnSameTile(dungeon.getPlayer(), this)) {
            goal.toggleExitOn();
        } else {
            goal.toggleExitOff();
        }
    }

}