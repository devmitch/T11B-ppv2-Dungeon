package unsw.dungeon;

public class PhasePotion extends Entity implements Potion {
    private int numberOfStepsLeft;

    public PhasePotion(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y, false, false, true);
        this.numberOfStepsLeft = 16;
    }

    /**
     * Decrement the number of steps before the potion expires.
     */
    public void decrementNumberOfSteps() {
        this.numberOfStepsLeft -= 1;
    }

    /**
     * @return true if the potion is still active, false otherwise.
     */
    public boolean isActive() {
        return numberOfStepsLeft > 0;
    }

    /**
     * @return the number of steps left for the potion.
     */
    public int getStepsLeft() {
        return this.numberOfStepsLeft;
    }
}
