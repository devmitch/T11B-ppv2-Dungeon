package unsw.dungeon;

public interface GoalObserver {
    
    /**
     * Update this object based on changes in the given subject.
     * 
     * @param subject
     */
    public void update(GoalSubject subject);

}