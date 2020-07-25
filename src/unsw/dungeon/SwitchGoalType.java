package unsw.dungeon;

public class SwitchGoalType implements GoalType, GoalObserver {

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

    @Override
    public void update(GoalSubject subject) {
        if (subject instanceof FloorSwitch) {
            FloorSwitch floorSwitch = (FloorSwitch) subject;
            
            if (floorSwitch.getIsTracked()) {
                if (floorSwitch.getSwitch()) {
                    incrementActiveSwitches();
                } else {
                    decrementActiveSwitches();
                }
            } else {
                incrementSwitchesNeeded();
                floorSwitch.setIsTracked(true);
            }
        }
    }

    /**
     * Increments the count of how many switches are active.
     */
    private void incrementActiveSwitches() {
        switchesActive++;
    }

    /**
     * Decrements the count of how many switches active.
     */
    private void decrementActiveSwitches() {
        switchesActive--;
    }

    /**
     * Increments the total number of switches that need to be active.
     */
    private void incrementSwitchesNeeded() {
        switchesNeeded++;
    }

}