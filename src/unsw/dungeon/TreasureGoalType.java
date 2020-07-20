package unsw.dungeon;

public class TreasureGoalType implements GoalType {

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

    /**
     * Increments the count of how much treasure has been collected.
     */
    public void incrementTreasureCount() {
        currentTreasure++;
    }

    /**
     * Increments the count of how much treasure needs to be collected. 
     */
    public void incrementTreasureNeeded() {
        treasureNeeded++;
    }
    
}