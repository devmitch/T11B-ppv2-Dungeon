package unsw.dungeon;

public class InvincibilityPotion extends Entity {

    private int numberOfStepsLeft;

    public InvincibilityPotion(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y, false, false, true);
        this.numberOfStepsLeft = 16;
    }

    public void decrementNumberOfSteps() {
        this.numberOfStepsLeft -= 1;
        System.out.println(numberOfStepsLeft + " steps left with invincibility.");
    }

    public boolean isInvincible() {
        return numberOfStepsLeft > 0;
    }

    public int getStepsLeft() {
        return this.numberOfStepsLeft;
    }

}
