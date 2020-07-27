package unsw.dungeon;

public class Boulder extends Entity {

    private Movement movement;
    private FloorSwitch fs;

    public Boulder(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y, true, true, false);
        this.movement = new Movement(dungeon, this);
        this.fs = findFloorSwitchOnTile();
        if (fs != null) {
            fs.setSwitch(true);
        }
    }

    /**
     * @return the floor switch that is occupies the same tile as this boulder.
     */
    private FloorSwitch findFloorSwitchOnTile() {
        for (Entity entity : dungeon.getEntitiesOnTile(getX(), getY())) {
            if (entity instanceof FloorSwitch) {
                return (FloorSwitch) entity;
            }
        }
        return null;
    }

    @Override
    public void interactWith(Entity e, Direction D) {
        if (e instanceof Player) {
            movement.moveInDirection(D);
        }
    }

    @Override
    public void updateState() {
        // check if still on floor switch
        if (fs != null) {
            if (!dungeon.areEntitiesOnSameTile(this, fs)) {
                fs.setSwitch(false);
                fs = null;
            }
        }
    }

    /**
     * Sets the floor switch for this boulder. Deactivates the previous one and activates the
     * current one.
     * 
     * @param fs the floor switch that the boulder is on.
     */
    public void setFloorSwitch(FloorSwitch fs) {
        if (this.fs != null) {
            this.fs.setSwitch(false);
        }
        this.fs = fs;
        this.fs.setSwitch(true);
    }

}