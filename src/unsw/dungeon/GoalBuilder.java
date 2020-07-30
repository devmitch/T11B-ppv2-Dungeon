package unsw.dungeon;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GoalBuilder {
    
    private TreasureGoalType treasureGoalType;
    private EnemyGoalType enemyGoalType;
    private ExitGoalType exitGoalType;
    private SwitchGoalType switchGoalType;

    private ObservableList<GoalType> dungeonGoals;

    public GoalBuilder() {
        treasureGoalType = new TreasureGoalType();
        enemyGoalType = new EnemyGoalType();
        exitGoalType = new ExitGoalType();
        switchGoalType = new SwitchGoalType();

        dungeonGoals = FXCollections.observableArrayList(new ArrayList<GoalType>());
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
                    addGoalToDungeonGoals(exitGoalType);
                    return new LeafGoal(exitGoalType);
                case "enemies":
                    addGoalToDungeonGoals(enemyGoalType);
                    return new LeafGoal(enemyGoalType);
                case "boulders":
                    addGoalToDungeonGoals(switchGoalType);
                    return new LeafGoal(switchGoalType);
                default:
                    addGoalToDungeonGoals(treasureGoalType);
                    return new LeafGoal(treasureGoalType);
            }
        }
    }

    private void addGoalToDungeonGoals(GoalType goal) {
        if (!dungeonGoals.contains(goal)) {
            dungeonGoals.add(goal);
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

    public ObservableList<GoalType> getDungeonGoals() {
        return dungeonGoals;
    }

}