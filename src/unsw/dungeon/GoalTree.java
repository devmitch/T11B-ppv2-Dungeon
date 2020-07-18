package unsw.dungeon;

import java.util.List;

public class GoalTree {
    private List<Goal> goals;
    private TreeComponent root;

    public boolean isComplete() {
        return root.isSatisfied();
    }
}