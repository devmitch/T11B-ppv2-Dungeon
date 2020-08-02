package unsw.dungeon;

public class InvincibilityPotion extends Potion {

    public InvincibilityPotion(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
        setNumbersOfStepsLeft(16);
    }

}
