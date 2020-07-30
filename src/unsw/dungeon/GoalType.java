package unsw.dungeon;

import javafx.beans.property.BooleanProperty;

public interface GoalType {

    /**
     * @return true if the goal is satisfied, false otherwise.
     */
    public boolean isSatisfied();
    
    public BooleanProperty getIsSatisfiedProperty();
}