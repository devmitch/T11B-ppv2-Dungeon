package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Sword extends Entity {

    private IntegerProperty durability;

    public Sword(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y, false, false, true);
        durability = new SimpleIntegerProperty();
        setDurability(5);
    }

    /**
     * @return true if the sword is broken, false otherwise.
     */
    public boolean isBroken() {
        return getDurability() <= 0;
    }

    /**
     * Decrements the durability of the sword.
     */
    public void swing() {
        setDurability(getDurability() - 1);
    }

    public int getDurability() {
        return this.durability.get();
    }

    public void setDurability(int durability) {
        this.durability.set(durability);
    }

    public IntegerProperty getDurabilityProperty() {
        return durability;
    }
    
}