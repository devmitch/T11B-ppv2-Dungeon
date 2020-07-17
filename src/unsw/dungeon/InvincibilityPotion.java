package unsw.dungeon;

public class InvincibilityPotion extends Entity {

    public InvincibilityPotion(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y, false, false, true);
    }
    
}