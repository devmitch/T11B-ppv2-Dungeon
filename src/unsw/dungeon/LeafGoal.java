package unsw.dungeon;

public class LeafGoal implements Goal {
    
    private GoalType goal;

    public LeafGoal(GoalType goalType) {
        this.goal = goalType;
    }

    @Override
    public boolean isSatisfied() {
        return goal.isSatisfied();
    }
}