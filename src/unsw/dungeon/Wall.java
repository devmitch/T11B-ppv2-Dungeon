package unsw.dungeon;

public class Wall extends Entity {

    public Wall(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y, true, false, false);
    }

}
