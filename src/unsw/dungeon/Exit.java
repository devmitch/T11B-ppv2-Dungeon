package unsw.dungeon;

public class Exit extends Entity {

    public Exit(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y, false, true, false);
    }
    
    @Override
    public void interactWith(Entity e, Direction d) {
        if (e instanceof Boulder) {
            this.dungeon.removeEntity(e);
        }
    }

}