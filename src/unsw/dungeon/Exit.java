package unsw.dungeon;

public class Exit extends Entity {
    private ExitGoalType goal;

    public Exit(Dungeon dungeon, int x, int y, ExitGoalType goal) {
        super(dungeon, x, y, false, true, false);
        this.goal = goal;
    }
    
    public void updateAtExitState() {
        if (getX() == dungeon.getPlayer().getX() && getY() == dungeon.getPlayer().getY()) {
            goal.toggleExitOn();
        } else {
            goal.toggleExitOff();
        }
    }

}