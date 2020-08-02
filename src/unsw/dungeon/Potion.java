package unsw.dungeon;

public interface Potion {
    public void decrementNumberOfSteps();

    public boolean isActive();

    public int getStepsLeft();
}
