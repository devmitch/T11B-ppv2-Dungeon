package unsw.dungeon;

public class EnemyGoalType implements GoalType, GoalObserver {
    private int enemiesKilled;
    private int enemiesNeeded;

    public EnemyGoalType() {
        this.enemiesNeeded = 0;
        this.enemiesKilled = 0;
    }

    @Override
    public boolean isSatisfied() {
        return enemiesKilled >= enemiesNeeded;
    }

    /**
     * Increments the count of how many enemies have been killed.
     */
    public void incrementEnemiesKilled() {
        enemiesKilled++;
    }

    /**
     * Increments the count of how many enemies need to be killed.
     */
    public void incrementEnemiesNeeded() {
        enemiesNeeded++;
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
}