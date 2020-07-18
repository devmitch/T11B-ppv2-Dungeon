package unsw.dungeon;

import java.util.List;
import java.util.ArrayList;

public class CompositeGoal implements Goal {

    private List<Goal> children;
    private boolean isConjunction;

    public CompositeGoal(boolean isConjunction) {
        this.children = new ArrayList<>();
        this.isConjunction = isConjunction;
    }

    @Override
    public boolean isSatisfied() {
        boolean conjunction = true;
        boolean disjunction = false;
        for (Goal child : this.children) {
            conjunction = conjunction && child.isSatisfied();
            disjunction = disjunction || child.isSatisfied();
        }
        return isConjunction ? conjunction : disjunction;
    }

    public void addGoal(Goal goal) {
        children.add(goal);
    }

}