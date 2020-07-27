package unsw.dungeon;

public interface Potion {
    public void useStep();

    public boolean isActive();

    public int getStepsLeft();
}