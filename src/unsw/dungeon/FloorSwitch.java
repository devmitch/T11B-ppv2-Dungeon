package unsw.dungeon;

import java.util.ArrayList;

public class FloorSwitch extends Entity implements GoalSubject {

    private ArrayList<GoalObserver> goals;
    private boolean isActive;
    private boolean isTracked;

    public FloorSwitch(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y, false, true, false);

        goals = new ArrayList<>();
        isActive = false;
    }
    
    @Override
    public void interactWith(Entity e, Direction D) {
        if (e instanceof Boulder) {
            Boulder b = (Boulder) e;
            b.setFloorSwitch(this);
        }
    }

    @Override
    public void attach(GoalObserver observer) {
        if (!(goals.contains(observer) || observer == null)) {
            goals.add(observer);
            observer.update(this);
        }
    }

    @Override
    public void detach(GoalObserver observer) {
        goals.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (GoalObserver goal : goals) {
            goal.update(this);
        }
    }

    /**
     * Changes the state of the switch to active or inactive.
     * 
     * @param isActive true if the switch is to be active, false otherwise.
     */
    public void setSwitch(boolean isActive) {
        this.isActive = isActive;
        notifyObservers();
    }

    public boolean getSwitch() {
        return isActive;
    }

    public void setIsTracked(boolean isTracked) {
        this.isTracked = isTracked;
    }

    public boolean getIsTracked() {
        return isTracked;
    }

}