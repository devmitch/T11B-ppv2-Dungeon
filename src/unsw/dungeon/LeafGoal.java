package unsw.dungeon;

public class LeafGoal implements Goal {
    private GoalType goal;

    @Override
    public boolean isSatisfied() {
        return goal.isSatisfied();
    }
}