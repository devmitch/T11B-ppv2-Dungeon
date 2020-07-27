package unsw.dungeon;

public class TreasureGoalType implements GoalType, GoalObserver {

    private int currentTreasure;
    private int treasureNeeded;

    public TreasureGoalType() {
        this.treasureNeeded = 0;
        this.currentTreasure = 0;
    }

    @Override
    public boolean isSatisfied() {
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
    }

    /**
     * Increments the count of how much treasure needs to be collected. 
     */
    private void incrementTreasureNeeded() {
        treasureNeeded++;
    }
    
}