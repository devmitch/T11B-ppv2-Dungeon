package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class EnemyGoalType implements GoalType, GoalObserver {

    private IntegerProperty enemiesLeftProperty;
    private BooleanProperty isSatisfiedProperty;

    private int enemiesKilled;
    private int enemiesNeeded;

    public EnemyGoalType() {
        enemiesLeftProperty = new SimpleIntegerProperty();
        isSatisfiedProperty = new SimpleBooleanProperty();
        this.enemiesNeeded = 0;
        this.enemiesKilled = 0;
    }

    @Override
    public boolean isSatisfied() {
        isSatisfiedProperty.set(enemiesKilled >= enemiesNeeded);
        return enemiesKilled >= enemiesNeeded;
    }

    /**
     * Increments the count of how many enemies have been killed.
     */
    public void incrementEnemiesKilled() {
        enemiesKilled++;
        updateEnemiesLeft();
    }

    /**
     * Increments the count of how many enemies need to be killed.
     */
    public void incrementEnemiesNeeded() {
        enemiesNeeded++;
        updateEnemiesLeft();
    }

    @Override
    public void update(GoalSubject subject) {
        if (subject instanceof Enemy) {
            Enemy enemy = (Enemy) subject;

            if (enemy.isDead()) {
                incrementEnemiesKilled();
            } else {
                incrementEnemiesNeeded();
            }
        }
    }

    private void updateEnemiesLeft() {
        enemiesLeftProperty.set(enemiesNeeded - enemiesKilled);
    }

    public IntegerProperty getEnemiesLeftProperty() {
        return this.enemiesLeftProperty;
    }

    @Override
    public BooleanProperty getIsSatisfiedProperty() {
        return this.isSatisfiedProperty;
    }
}