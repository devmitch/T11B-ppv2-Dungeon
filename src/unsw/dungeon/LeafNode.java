package unsw.dungeon;

public class LeafNode implements TreeComponent {
    private Goal goal;

    @Override
    public boolean isSatisfied() {
        return goal.isSatisfied();
    }
}