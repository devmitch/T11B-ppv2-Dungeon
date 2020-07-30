package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class TreasureGoalType implements GoalType, GoalObserver {

    private IntegerProperty treasureLeftProperty;
    private BooleanProperty isSatisfiedProperty;

    private int currentTreasure;
    private int treasureNeeded;

    public TreasureGoalType() {
        treasureLeftProperty = new SimpleIntegerProperty();
        isSatisfiedProperty = new SimpleBooleanProperty();
        this.treasureNeeded = 0;
        this.currentTreasure = 0;
    }

    @Override
    public boolean isSatisfied() {
        isSatisfiedProperty.set(currentTreasure >= treasureNeeded);
        return currentTreasure >= treasureNeeded;
    }

    @Override
    public void update(GoalSubject subject) {
        if (subject instanceof Treasure) {
            Treasure treasure = (Treasure) subject;
            
            if (treasure.getIsPickedUp()) {
                incrementTreasureCount();
            } else {
                incrementTreasureNeeded();
            }
        }
    }

    /**
     * Increments the count of how much treasure has been collected.
     */
    private void incrementTreasureCount() {
        currentTreasure++;
        setTreasureNeeded(treasureNeeded - currentTreasure);
    }

    /**
     * Increments the count of how much treasure needs to be collected. 
     */
    private void incrementTreasureNeeded() {
        treasureNeeded++;
        setTreasureNeeded(treasureNeeded - currentTreasure);
    }

    public void setTreasureNeeded(int currentTreasure) {
        this.treasureLeftProperty.set(currentTreasure);
    }

    public IntegerProperty getCurrentTreasureProperty() {
        return this.treasureLeftProperty;
    }

    @Override
    public BooleanProperty getIsSatisfiedProperty() {
        return this.isSatisfiedProperty;
    }
    
}