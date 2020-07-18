package unsw.dungeon;

import java.util.List;

public class CompositeNode implements TreeComponent {
    private List<LeafNode> children;
    private boolean isConjunction;

    @Override
    public boolean isSatisfied() {
        boolean conjunction = true;
        boolean disjunction = false;
        for (LeafNode leaf : this.children) {
            conjunction = conjunction && leaf.isSatisfied();
            disjunction = disjunction || leaf.isSatisfied();
        }
        return isConjunction ? conjunction : disjunction;
    }
}