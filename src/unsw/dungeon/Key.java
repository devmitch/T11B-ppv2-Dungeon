package unsw.dungeon;

public class Key extends Entity {

    private int id;
    
    public Key(Dungeon dungeon, int x, int y, int id) {
        super(dungeon, x, y, false, false, true);
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

}