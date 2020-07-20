package unsw.dungeon;

public class ExitGoalType implements GoalType {

    private boolean atExit;

    public ExitGoalType() {
        this.atExit = false;
    }

    @Override
    public boolean isSatisfied() {
        return atExit;
    }

    /**
     * Sets the exit goal to be completed.
     */
    public void toggleExitOn() {
        this.atExit = true;
    }

    /**
     * Sets the exit goal to not be completed.
     */
    public void toggleExitOff() {
        this.atExit = false;
    }
}