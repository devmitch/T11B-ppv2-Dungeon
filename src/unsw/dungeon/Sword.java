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

    /**
     * @return true if the sword is broken, false otherwise.
     */
    public boolean isBroken() {
        return this.durability > 0 ? false : true;
    }

    /**
     * Decrements the durability of the sword.
     */
    public void swing() {
        this.durability--;
    }
    
}