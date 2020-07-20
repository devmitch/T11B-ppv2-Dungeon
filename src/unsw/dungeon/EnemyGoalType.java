package unsw.dungeon;

public class EnemyGoalType implements GoalType {
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

    public void incrementEnemiesKilled() {
        enemiesKilled++;
    }

    public void incrementEnemiesNeeded() {
        enemiesNeeded++;
    }
}