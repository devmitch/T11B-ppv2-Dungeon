package unsw.dungeon;

public class SwitchGoalType implements GoalType {

    private int switchesActive;
    private int switchesNeeded;

    public SwitchGoalType() {
        this.switchesNeeded = 0;
        this.switchesActive = 0;
    }

    @Override
    public boolean isSatisfied() {
        return switchesActive >= switchesNeeded;
    }

    /**
     * Increments the count of how many switches are active.
     */
    public void incrementActiveSwitches() {
        switchesActive++;
    }

    /**
     * Decrements the count of how many switches active.
     */
    public void decrementActiveSwitches() {
        switchesActive--;
    }

    /**
     * Increments the total number of switches that need to be active.
     */
    public void incrementSwitchesNeeded() {
        switchesNeeded++;
    }
}