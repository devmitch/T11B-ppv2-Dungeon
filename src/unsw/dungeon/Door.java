package unsw.dungeon;

public class Door extends Entity {

    private DoorState currentState;
    private int id;

    public Door(Dungeon dungeon, int x, int y, int id) {
        super(dungeon, x, y, true, true, false);
        this.id = id;
        this.currentState = new ClosedDoorState();
    }

    @Override
    public void interactWith(Entity e, Direction D) {
        if (e instanceof Player) {
            Player p = (Player) e;
            Key key = p.requestKey(this.id);
            if (key != null) {
                this.currentState = new OpenDoorState();
            }
        }
    }

    @Override
    public boolean isInteractable() {
        return this.currentState.isInteractable();
    }

    @Override
    public boolean isObstruction() {
        return this.currentState.isObstruction();
    }
}