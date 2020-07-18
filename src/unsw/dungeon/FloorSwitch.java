package unsw.dungeon;

public class FloorSwitch extends Entity {

    // think of this as a primitive floor switch goal count.
    private static int activeCount = 0;

    public FloorSwitch(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y, false, true, false);
    }
    
    @Override
    public void interactWith(Entity e, Direction D) {
        if (e instanceof Boulder) {
            Boulder b = (Boulder) e;
            b.setFloorSwitch(this);
        }
    }

    public void activateSwitch() {
        FloorSwitch.activeCount += 1;
        System.out.println("Activated switch. " + FloorSwitch.activeCount);
    }

    public void deactivateSwitch() {
        FloorSwitch.activeCount -= 1;
        System.out.println("Deactivated switch. " + FloorSwitch.activeCount);
    }

}