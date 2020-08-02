package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class SwitchGoalType implements GoalType, GoalObserver {

    private IntegerProperty switchesLeftProperty;
    private BooleanProperty isSatisfiedProperty;
    
    private int switchesActive;
    private int switchesNeeded;

    public SwitchGoalType() {
        this.isSatisfiedProperty = new SimpleBooleanProperty();
        this.switchesLeftProperty = new SimpleIntegerProperty();
        this.switchesNeeded = 0;
        this.switchesActive = 0;
    }

    @Override
    public boolean isSatisfied() {
        isSatisfiedProperty.set(switchesActive >= switchesNeeded);
        return switchesActive >= switchesNeeded;
    }

    @Override
    public void update(GoalSubject subject) {
        if (subject instanceof FloorSwitch) {
            FloorSwitch floorSwitch = (FloorSwitch) subject;

            if (floorSwitch.getIsTracked()) {
                if (floorSwitch.getSwitch()) {
                    incrementActiveSwitches();
                } else {
                    decrementActiveSwitches();
                }
            } else {
                incrementSwitchesNeeded();
                floorSwitch.setIsTracked(true);
            }
        }
    }

    /**
     * Increments the count of how many switches are active.
     */
    private void incrementActiveSwitches() {
        switchesActive++;
        updateSwitchesLeft();
    }

    /**
     * Decrements the count of how many switches active.
     */
    private void decrementActiveSwitches() {
        switchesActive--;
        updateSwitchesLeft();
    }

    /**
     * Increments the total number of switches that need to be active.
     */
    private void incrementSwitchesNeeded() {
        switchesNeeded++;
        updateSwitchesLeft();
    }

    private void updateSwitchesLeft() {
        switchesLeftProperty.set(switchesNeeded - switchesActive);
    }

    public IntegerProperty getSwitchesLeftProperty() {
        return switchesLeftProperty;
    }

    @Override
    public BooleanProperty getIsSatisfiedProperty() {
        return isSatisfiedProperty;
    }

}