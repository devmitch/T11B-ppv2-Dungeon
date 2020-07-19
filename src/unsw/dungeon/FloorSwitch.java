package unsw.dungeon;

public class FloorSwitch extends Entity {

    private SwitchGoalType goal;

    public FloorSwitch(Dungeon dungeon, int x, int y, SwitchGoalType goal) {
        super(dungeon, x, y, false, true, false);
        this.goal = goal;
    }
    
    @Override
    public void interactWith(Entity e, Direction D) {
        if (e instanceof Boulder) {
            Boulder b = (Boulder) e;
            b.setFloorSwitch(this);
        }
    }

    public void activateSwitch() {
        goal.incrementActiveSwitches();
    }

    public void deactivateSwitch() {
        goal.decrementActiveSwitches();
    }

}