package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Potion extends Entity {

    private IntegerProperty numberOfStepsLeft;

    public Potion(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y, false, false, true);
        numberOfStepsLeft = new SimpleIntegerProperty();
    }

    public void decrementNumberOfSteps() {
        setNumbersOfStepsLeft(getStepsLeft() - 1);
    }

    public boolean isActive() {
        return getStepsLeft() > 0;
    }

    public int getStepsLeft() {
        return numberOfStepsLeft.get();
    }

    public void setNumbersOfStepsLeft(int numberOfStepsLeft) {
        this.numberOfStepsLeft.set(numberOfStepsLeft);
    }

    public IntegerProperty getStepsLeftProperty() {
        return numberOfStepsLeft;
    }

}
