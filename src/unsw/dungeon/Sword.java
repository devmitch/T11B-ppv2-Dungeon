package unsw.dungeon;

public class Sword extends Entity {

    private int durability;

    public Sword(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y, false, false, true);
        this.durability = 5;
    }

    public int getDurability() {
        return this.durability;
    }

    public boolean isBroken() {
        return this.durability > 0;
    }

    public void swing() {
        this.durability--;
    }
    
}