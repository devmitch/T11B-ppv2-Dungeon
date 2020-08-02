package unsw.dungeon;

public class PhasePotion extends Potion {

    public PhasePotion(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
        setNumbersOfStepsLeft(16);
    }

}
