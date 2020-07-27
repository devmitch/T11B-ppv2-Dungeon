package unsw.dungeon;

import java.util.ArrayList;

public class Exit extends Entity implements GoalSubject {

    private ArrayList<GoalObserver> goals;
    private boolean isAtExit;

    public Exit(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y, false, true, false);
        this.goals = new ArrayList<>();
        updateAtExitState();
    }
    
    @Override
    public void interactWith(Entity e, Direction d) {
        if (e instanceof Boulder) {
            this.dungeon.removeEntity(e);
        }
    }
    
    /**
     * Checks if the player and exit still occupy the same tile, updates the goal to represent the
     * state.
     */
    public void updateAtExitState() {
        if (dungeon.areEntitiesOnSameTile(dungeon.getPlayer(), this)) {
            isAtExit = true;
        } else {
            isAtExit = false;
        }
        notifyObservers();
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
        for (GoalObserver observer : goals) {
            observer.update(this);
        }
    }

    public boolean isAtExit() {
        return isAtExit;
    }

}