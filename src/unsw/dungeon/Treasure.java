package unsw.dungeon;

public class Treasure extends Entity {

    private TreasureGoal goal;

    public Treasure(Dungeon dungeon, int x, int y, TreasureGoal goal) {
        super(dungeon, x, y, false, false, true);
        this.goal = goal;
        this.goal.incrementTreasureNeeded();
    }    

    @Override
    public void updateState() {
        this.goal.incrementTreasureCount();
    }
    
}