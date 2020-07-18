package unsw.dungeon;

public class Treasure extends Entity {

    private TreasureGoalType goal;

    public Treasure(Dungeon dungeon, int x, int y, TreasureGoalType goal) {
        super(dungeon, x, y, false, false, true);
        this.goal = goal;
        this.goal.incrementTreasureNeeded();
    }    

    @Override
    public void updateState() {
        this.goal.incrementTreasureCount();
    }
    
}