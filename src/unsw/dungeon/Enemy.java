package unsw.dungeon;

public class Enemy extends Entity {

    public Enemy(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y, false, true, false);
    }
    
    @Override
    public void interactWith(Entity e, Direction D) {
        if (e instanceof Player) {
            ((Player)e).duel(this);
        }
    }
}