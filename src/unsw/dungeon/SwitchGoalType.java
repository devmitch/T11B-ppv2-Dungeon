package unsw.dungeon;

public class SwitchGoalType extends GoalType {
    private boolean atExit;

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

    public void incrementActiveSwitches() {
        switchesActive++;
    }

    public void decrementActiveSwitches() {
        switchesActive--;
    }

    public void incrementSwitchesNeeded() {
        switchesNeeded++;
    }
}