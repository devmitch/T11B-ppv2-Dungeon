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

    public boolean attemptSwing() {
        if (this.durability > 0) {
            this.durability--;
            return true;
        } else {
            return false;
        }
    }
    
}