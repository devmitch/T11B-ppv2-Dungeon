package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class InvincibilityPotion extends Entity implements Potion{

    private IntegerProperty numberOfStepsLeft;

    public InvincibilityPotion(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y, false, false, true);
        numberOfStepsLeft = new SimpleIntegerProperty();
        setNumberOfStepsLeft(16);
    }

    /**
     * Decrement the number of steps before the potion expires.
     */
    public void decrementNumberOfSteps() {
        setNumberOfStepsLeft(getStepsLeft() - 1);
    }

    /**
     * @return true if the potion is still active, false otherwise.
     */
    public boolean isActive() {
        return getStepsLeft() > 0;
    }

    /**
     * @return the number of steps left for the potion.
     */
    public int getStepsLeft() {
        return this.numberOfStepsLeft.get();
    }

    public void setNumberOfStepsLeft(int numberOfStepsLeft) {
        this.numberOfStepsLeft.set(numberOfStepsLeft);
    }

    public IntegerProperty getStepsLeftProperty() {
        return numberOfStepsLeft;
    }

}
