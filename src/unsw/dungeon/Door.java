package unsw.dungeon;

public class Door extends Entity {
    
    private int id;

    public Door(Dungeon dungeon, int x, int y, int id) {
        super(dungeon, x, y, true, true, false);
        this.id = id;
    }

    @Override
    public void interactWith(Entity e, Direction D) {
        if (e instanceof Player) {
            Player p = (Player) e;
            Key key = p.requestKey(this.id);
            if (key != null) {
                this.isObstruction = false;
                this.interactable = false;
            }
        }
    }
}