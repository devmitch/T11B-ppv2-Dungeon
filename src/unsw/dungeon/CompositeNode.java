package unsw.dungeon;

import java.util.List;

public class CompositeNode implements TreeComponent {
    private List<TreeComponent> children;
    private boolean isConjunction;

    @Override
    public boolean isSatisfied() {
        boolean conjunction = true;
        boolean disjunction = false;
        for (TreeComponent child : this.children) {
            conjunction = conjunction && child.isSatisfied();
            disjunction = disjunction || child.isSatisfied();
        }
        return isConjunction ? conjunction : disjunction;
    }
}