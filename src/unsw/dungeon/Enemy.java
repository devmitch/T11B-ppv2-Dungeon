package unsw.dungeon;

public class Enemy extends Entity {

    public Enemy(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y, false, true, false);
    }
    
}