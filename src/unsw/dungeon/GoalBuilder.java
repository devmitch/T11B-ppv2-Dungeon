package unsw.dungeon;

import org.json.JSONArray;
import org.json.JSONObject;

public class GoalBuilder {
    
    private TreasureGoalType treasureGoalType;
    private EnemyGoalType enemyGoalType;
    private ExitGoalType exitGoalType;
    private SwitchGoalType switchGoalType;

    public GoalBuilder() {
        treasureGoalType = new TreasureGoalType();
        enemyGoalType = new EnemyGoalType();
        exitGoalType = new ExitGoalType();
        switchGoalType = new SwitchGoalType();
    }

    /**
     * Converts the given json object into a goal object that represents the goal structure.
     * 
     * @param json the given json object.
     * @return a goal.
     */
    public Goal parseJsonIntoGoal(JSONObject json) {
        String goalType = json.getString("goal");
        // composite case
        if (goalType.equals("AND") || goalType.equals("OR")) {

            CompositeGoal goal = new CompositeGoal(goalType);

            JSONArray subGoals = json.getJSONArray("subgoals");
            for (int i = 0; i < subGoals.length(); i++) {
                goal.addGoal(parseJsonIntoGoal(subGoals.getJSONObject(i)));
            }

            return goal;
        } else {
            switch (goalType) {
                case "exit":
                    return new LeafGoal(exitGoalType);
                case "enemies":
                    return new LeafGoal(enemyGoalType);
                case "boulders":
                    return new LeafGoal(switchGoalType);
                default:
                    return new LeafGoal(treasureGoalType);
            }
        }
    }

    public TreasureGoalType getTreasureGoalType() {
        return treasureGoalType;
    }

    public EnemyGoalType getEnemyGoalType() {
        return enemyGoalType;
    }

    public ExitGoalType getExitGoalType() {
        return exitGoalType;
    }

    public SwitchGoalType getSwitchGoalType() {
        return switchGoalType;
    }

}