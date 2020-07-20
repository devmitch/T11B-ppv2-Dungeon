package unsw.dungeon;

public class FloorSwitch extends Entity {

    private SwitchGoalType goal;

    public FloorSwitch(Dungeon dungeon, int x, int y, SwitchGoalType goal) {
        super(dungeon, x, y, false, true, false);
        this.goal = goal;
        this.goal.incrementSwitchesNeeded();
    }
    
    @Override
    public void interactWith(Entity e, Direction D) {
        if (e instanceof Boulder) {
            Boulder b = (Boulder) e;
            b.setFloorSwitch(this);
        }
    }

    /**
     * Activates the switch.
     */
    public void activateSwitch() {
        goal.incrementActiveSwitches();
    }

    /**
     * Deactivates the switch.
     */
    public void deactivateSwitch() {
        goal.decrementActiveSwitches();
    }

}