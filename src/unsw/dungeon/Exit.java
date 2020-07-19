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
            System.out.println("attempting to remove boulder");
            this.dungeon.removeEntity(e);
        }
    }
    
    public void updateAtExitState() {
        if (getX() == dungeon.getPlayer().getX() && getY() == dungeon.getPlayer().getY()) {
            goal.toggleExitOn();
        } else {
            goal.toggleExitOff();
        }
    }

}