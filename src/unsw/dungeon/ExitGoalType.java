package unsw.dungeon;

public class ExitGoalType extends GoalType {
    private boolean atExit;

    public ExitGoalType() {
        this.atExit = false;
    }

    @Override
    public boolean isSatisfied() {
        return atExit;
    }

    public void toggleExitOn() {
        this.atExit = true;
    }

    public void toggleExitOff() {
        this.atExit = false;
    }
}