package unsw.dungeon;

public abstract class Goal {
    private GoalTree tree;
    public abstract boolean isSatisfied();
}