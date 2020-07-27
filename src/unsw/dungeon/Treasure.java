package unsw.dungeon;

import java.util.ArrayList;

public class Treasure extends Entity implements GoalSubject {

    private ArrayList<GoalObserver> goals;
    private boolean isPickedUp;

    public Treasure(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y, false, false, true);
        goals = new ArrayList<>();
        isPickedUp = false;
    }    

    @Override
    public void updateState() {
        isPickedUp = true;
        notifyObservers();
    }

    @Override
    public void attach(GoalObserver observer) {
        // each time we attach an observer, increment the amount of treasure we need for that goal
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

    public boolean getIsPickedUp() {
        return isPickedUp;
    }
    
}