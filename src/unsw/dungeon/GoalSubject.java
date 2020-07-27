package unsw.dungeon;

public interface GoalSubject {
    
    /**
     * Attach the given observer to this object.
     * 
     * @param observer
     */
    public void attach(GoalObserver observer);

    /**
     * Detach the given observer from this object.
     * 
     * @param observer
     */
    public void detach(GoalObserver observer);

    /**
     * Notify the observers of any changes.
     */
    public void notifyObservers();

}