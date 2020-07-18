package unsw.dungeon;

import java.util.List;

public class CompositeGoal implements Goal {
    private List<Goal> children;
    private boolean isConjunction;

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
}