package unsw.dungeon;

public class Boulder extends Entity {
    private Dungeon dungeon;
    private Movement movement;

    public Boulder(Dungeon dungeon, int x, int y) {
        super(x, y, true);
        this.dungeon = dungeon;
        this.movement = new Movement(dungeon, this);
    }
}