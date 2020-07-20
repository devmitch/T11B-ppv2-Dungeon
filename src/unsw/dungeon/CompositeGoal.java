package unsw.dungeon;

import java.util.List;
import java.util.ArrayList;

public class CompositeGoal implements Goal {

    private List<Goal> subGoals;
    private boolean isConjunction;

    public CompositeGoal(String isConjunctionString) {
        this(isConjunctionString.equals("AND") ? true : false);
    }

    public CompositeGoal(boolean isConjunction) {
        this.subGoals = new ArrayList<>();
        this.isConjunction = isConjunction;
    }

    @Override
    public boolean isSatisfied() {
        boolean conjunction = true;
        boolean disjunction = false;
        for (Goal child : this.subGoals) {
            conjunction = conjunction && child.isSatisfied();
            disjunction = disjunction || child.isSatisfied();
        }
        return isConjunction ? conjunction : disjunction;
    }

    /**
     * Adds the given goal to the list of goals.
     * 
     * @param goal the new sub goal.
     */
    public void addGoal(Goal goal) {
        subGoals.add(goal);
    }

}