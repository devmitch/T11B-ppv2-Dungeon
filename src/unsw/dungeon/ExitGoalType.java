package unsw.dungeon;

public class ExitGoalType implements GoalType, GoalObserver {

    private boolean atExit;

    public ExitGoalType() {
        this.atExit = false;
    }

    @Override
    public boolean isSatisfied() {
        return atExit;
    }

    @Override
    public void update(GoalSubject subject) {
        if (subject instanceof Exit) {
            atExit = ((Exit) subject).isAtExit();
        }
    }
}