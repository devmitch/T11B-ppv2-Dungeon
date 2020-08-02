package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class ExitGoalType implements GoalType, GoalObserver {

    private BooleanProperty atExitProperty;

    public ExitGoalType() {
        this.atExitProperty = new SimpleBooleanProperty();
    }

    @Override
    public boolean isSatisfied() {
        return atExitProperty.get();
    }

    @Override
    public void update(GoalSubject subject) {
        if (subject instanceof Exit) {
            atExitProperty.set(((Exit) subject).isAtExit());
        }
    }

    @Override
    public BooleanProperty getIsSatisfiedProperty() {
        return atExitProperty;
    }
}