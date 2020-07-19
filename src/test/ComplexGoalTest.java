package test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
            boulder.put("x", 1);
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

    public DungeonMockController setup2() {
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
            boulder.put("x", 1);
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
            goal.put("goal", "OR");
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

    public DungeonMockController setup3() {
        try {
            JSONObject json = new JSONObject();
            json.put("height", 1);
            json.put("width", 5);

            JSONArray entities = new JSONArray();

            JSONObject player = new JSONObject();
            player.put("x", 3);
            player.put("y", 0);
            player.put("type", "player");

            JSONObject exit = new JSONObject();
            exit.put("type", "exit");
            exit.put("x", 4);
            exit.put("y", 0);

            JSONObject floorSwitch = new JSONObject();
            floorSwitch.put("type", "switch");
            floorSwitch.put("x", 0);
            floorSwitch.put("y", 0);

            JSONObject boulder = new JSONObject();
            boulder.put("type", "boulder");
            boulder.put("x", 1);
            boulder.put("y", 0);

            JSONObject treasure = new JSONObject();
            treasure.put("type", "treasure");
            treasure.put("x", 2);
            treasure.put("y", 0);

            entities.put(player);
            entities.put(floorSwitch);
            entities.put(boulder);
            entities.put(treasure);
            entities.put(exit);

            json.put("entities", entities);

            JSONObject treasureGoal = new JSONObject();
            treasureGoal.put("goal", "treasure");

            JSONObject boulderGoal = new JSONObject();
            boulderGoal.put("goal", "boulders");

            JSONObject exitGoal = new JSONObject();
            exitGoal.put("goal", "exit");

            JSONArray secondSubGoals = new JSONArray();
            secondSubGoals.put(treasureGoal);
            secondSubGoals.put(boulderGoal);

            JSONObject subGoal = new JSONObject();
            subGoal.put("goal", "OR");
            subGoal.put("subgoals", secondSubGoals);

            JSONArray firstSubGoals = new JSONArray();
            firstSubGoals.put(exitGoal);
            firstSubGoals.put(subGoal);

            JSONObject goal = new JSONObject();
            goal.put("goal", "AND");
            goal.put("subgoals", firstSubGoals);
            
            json.put("goal-condition", goal);

            System.out.println(json.toString(4));

            DungeonMockControllerLoader dungeonLoader = new DungeonMockControllerLoader(json);

            DungeonMockController controller = dungeonLoader.loadController();
            return controller;
        } catch (Exception e) {
            return null;
        }
    }

    public DungeonMockController setup4() {
        try {
            JSONObject json = new JSONObject();
            json.put("height", 1);
            json.put("width", 5);

            JSONArray entities = new JSONArray();

            JSONObject player = new JSONObject();
            player.put("x", 3);
            player.put("y", 0);
            player.put("type", "player");

            JSONObject exit = new JSONObject();
            exit.put("type", "exit");
            exit.put("x", 4);
            exit.put("y", 0);

            JSONObject floorSwitch = new JSONObject();
            floorSwitch.put("type", "switch");
            floorSwitch.put("x", 0);
            floorSwitch.put("y", 0);

            JSONObject boulder = new JSONObject();
            boulder.put("type", "boulder");
            boulder.put("x", 1);
            boulder.put("y", 0);

            JSONObject treasure = new JSONObject();
            treasure.put("type", "treasure");
            treasure.put("x", 2);
            treasure.put("y", 0);

            entities.put(player);
            entities.put(floorSwitch);
            entities.put(boulder);
            entities.put(treasure);
            entities.put(exit);

            json.put("entities", entities);

            JSONObject treasureGoal = new JSONObject();
            treasureGoal.put("goal", "treasure");

            JSONObject boulderGoal = new JSONObject();
            boulderGoal.put("goal", "boulders");

            JSONObject exitGoal = new JSONObject();
            exitGoal.put("goal", "exit");

            JSONArray secondSubGoals = new JSONArray();
            secondSubGoals.put(treasureGoal);
            // secondSubGoals.put(boulderGoal);

            JSONObject subGoal = new JSONObject();
            subGoal.put("goal", "AND");
            subGoal.put("subgoals", secondSubGoals);

            JSONArray firstSubGoals = new JSONArray();
            // firstSubGoals.put(exitGoal);
            firstSubGoals.put(subGoal);

            JSONObject goal = new JSONObject();
            goal.put("goal", "AND");
            goal.put("subgoals", firstSubGoals);
            
            json.put("goal-condition", goal);

            System.out.println(json.toString(4));

            DungeonMockControllerLoader dungeonLoader = new DungeonMockControllerLoader(json);

            DungeonMockController controller = dungeonLoader.loadController();
            return controller;
        } catch (Exception e) {
            return null;
        }
    }

    public DungeonMockController setup5() {
        try {
            JSONObject json = new JSONObject();
            json.put("height", 1);
            json.put("width", 5);

            JSONArray entities = new JSONArray();

            JSONObject player = new JSONObject();
            player.put("x", 3);
            player.put("y", 0);
            player.put("type", "player");

            JSONObject exit = new JSONObject();
            exit.put("type", "exit");
            exit.put("x", 4);
            exit.put("y", 0);

            JSONObject floorSwitch = new JSONObject();
            floorSwitch.put("type", "switch");
            floorSwitch.put("x", 0);
            floorSwitch.put("y", 0);

            JSONObject boulder = new JSONObject();
            boulder.put("type", "boulder");
            boulder.put("x", 1);
            boulder.put("y", 0);

            JSONObject treasure = new JSONObject();
            treasure.put("type", "treasure");
            treasure.put("x", 2);
            treasure.put("y", 0);

            entities.put(player);
            entities.put(floorSwitch);
            entities.put(boulder);
            entities.put(treasure);
            entities.put(exit);

            json.put("entities", entities);

            JSONObject treasureGoal = new JSONObject();
            treasureGoal.put("goal", "treasure");

            JSONObject boulderGoal = new JSONObject();
            boulderGoal.put("goal", "boulders");

            JSONObject exitGoal = new JSONObject();
            exitGoal.put("goal", "exit");

            JSONArray secondSubGoals = new JSONArray();
            secondSubGoals.put(treasureGoal);
            secondSubGoals.put(boulderGoal);

            JSONObject subGoal = new JSONObject();
            subGoal.put("goal", "OR");
            subGoal.put("subgoals", secondSubGoals);

            JSONArray firstSubGoals = new JSONArray();
            firstSubGoals.put(treasureGoal);
            firstSubGoals.put(subGoal);

            JSONObject goal = new JSONObject();
            goal.put("goal", "AND");
            goal.put("subgoals", firstSubGoals);
            
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

        assertFalse(dungeon.completedGoal());

        // Pick up the treasure
        dungeon.getPlayer().move(Direction.LEFT);

        // Goal is not completed because we still need to move the boulder
        assertFalse(dungeon.completedGoal());

        dungeon.getPlayer().move(Direction.LEFT);
        assertTrue(dungeon.completedGoal());
    }

    // simple complex goal with OR
    @Test
    public void complexGoalWithOr() {
        DungeonMockController controller = setup2();

        Dungeon dungeon = controller.dungeon;

        // We have didn't completed anything yet
        assertFalse(dungeon.completedGoal());

        // Pick up the treasure, goal is now completed because it is an OR
        dungeon.getPlayer().move(Direction.LEFT);
        assertTrue(dungeon.completedGoal());
    }

    // goal with leafs and composites all the way through
    @Test
    public void complexGoalWithLeafsAndComposites() {
        DungeonMockController controller = setup3();

        Dungeon dungeon = controller.dungeon;
        assertFalse(dungeon.completedGoal());

        // Check that moving onto the exit doesn't complete the goal
        dungeon.getPlayer().move(Direction.RIGHT);
        assertFalse(dungeon.completedGoal());

        // Pick up the treasure, the goal passes because this goal is a part of the OR composite
        // goal, however we still need to go to the exit
        dungeon.getPlayer().move(Direction.LEFT);
        dungeon.getPlayer().move(Direction.LEFT);
        assertFalse(dungeon.completedGoal());

        // Go to the exit and now the goal is completed.
        dungeon.getPlayer().move(Direction.RIGHT);
        dungeon.getPlayer().move(Direction.RIGHT);
        assertTrue(dungeon.completedGoal());

    }

    // goal with an AND and then an AND and then an AND and then a leaf goal
    @Test
    public void goalWithPointlessComposites() {
        DungeonMockController controller = setup4();

        Dungeon dungeon = controller.dungeon;
        assertFalse(dungeon.completedGoal());

        // Pick up the treasure, the goals will now pass.
        dungeon.getPlayer().move(Direction.LEFT);
        assertTrue(dungeon.completedGoal());
    }

    // goal with duplicate goals
    @Test
    public void goalWithDuplicateGoals() {
        DungeonMockController controller = setup5();

        Dungeon dungeon = controller.dungeon;
        assertFalse(dungeon.completedGoal());

        // Pick up the treasure, the goals will now pass.
        dungeon.getPlayer().move(Direction.LEFT);
        assertTrue(dungeon.completedGoal());

        // both the treasure leaf and the treasure leaf in the OR pass so the goal is completed.
    }

}