package unsw.dungeon;

public class TreasureGoal extends Goal {

    private int currentTreasure;
    private int treasureNeeded;

    public TreasureGoal() {
        this.treasureNeeded = 0;
        this.currentTreasure = 0;
    }

    @Override
    public boolean isSatisfied() {
        return currentTreasure >= treasureNeeded;
    }

    public void incrementTreasureCount() {
        currentTreasure++;
    }

    public void incrementTreasureNeeded() {
        treasureNeeded++;
    }
    
}