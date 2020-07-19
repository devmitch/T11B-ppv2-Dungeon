package test;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import unsw.dungeon.*;

public class ComplexGoalTest {
    
    public DungeonMockController setup1() {
        try {
            JSONObject json = new JSONObject();
            json.put("height", 1);
            json.put("width", 4);

            JSONArray entities = new JSONArray();

            JSONObject player = new JSONObject();
            player.put("x", 3);
            player.put("y", 0);
            player.put("type", "player");

            JSONObject floorSwitch = new JSONObject();
            floorSwitch.put("type", "switch");
            floorSwitch.put("x", 0);
            floorSwitch.put("y", 0);

            JSONObject boulder = new JSONObject();
            boulder.put("type", "boulder");
            boulder.put("x", 0);
            boulder.put("y", 0);

            JSONObject treasure = new JSONObject();
            treasure.put("type", "treasure");
            treasure.put("x", 2);
            treasure.put("y", 0);

            entities.put(player);
            entities.put(floorSwitch);
            entities.put(boulder);
            entities.put(treasure);

            json.put("entities", entities);

            JSONObject treasureGoal = new JSONObject();
            treasureGoal.put("goal", "treasure");

            JSONObject boulderGoal = new JSONObject();
            boulderGoal.put("goal", "boulders");

            JSONArray subGoals = new JSONArray();
            subGoals.put(treasureGoal);
            subGoals.put(boulderGoal);

            JSONObject goal = new JSONObject();
            goal.put("goal", "AND");
            goal.put("subgoals", subGoals);
            
            json.put("goal-condition", goal);

            System.out.println(json.toString(4));

            DungeonMockControllerLoader dungeonLoader = new DungeonMockControllerLoader(json);

            DungeonMockController controller = dungeonLoader.loadController();
            return controller;
        } catch (Exception e) {
            return null;
        }
    }

    // simple complex goal with AND
    @Test
    public void complexGoalWithAnd() {
        DungeonMockController controller = setup1();

        Dungeon dungeon = controller.dungeon;

        
    }

    // simple complex goal with OR
    @Test
    public void complexGoalWithOr() {

    }

    // goal with an AND and then a leaf and then sub-goal with AND and OR

    // goal with an AND and then an AND and then an AND and then a leaf goal

}