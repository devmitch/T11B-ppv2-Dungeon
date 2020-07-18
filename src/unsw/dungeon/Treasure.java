package unsw.dungeon;

public class Treasure extends Entity {

    private static int NumberOfTreasure = 0;

    public Treasure(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y, false, false, true);
    }    

    @Override
    public void updateState() {
        Treasure.NumberOfTreasure += 1;
        System.out.println("Treasure count: " + Treasure.NumberOfTreasure);
    }
    
}