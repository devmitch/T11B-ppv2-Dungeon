package test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Test;

import unsw.dungeon.*;

public class ExitGoalTest {
    
    public DungeonMockController setup() {
        
        try {
            JSONObject json = new JSONObject();
            json.put("height", 1);
            json.put("width", 2);

            JSONArray entities = new JSONArray();

            JSONObject player = new JSONObject();
            player.put("x", 0);
            player.put("y", 0);
            player.put("type", "player");
            entities.put(player);

            JSONObject exit = new JSONObject();
            exit.put("x", 1);
            exit.put("y", 0);
            exit.put("type", "exit");
            entities.put(exit);

            json.put("entities", entities);

            JSONObject goal = new JSONObject();
            goal.put("goal", "exit");
            
            json.put("goal-condition", goal);

            DungeonMockControllerLoader dungeonLoader = new DungeonMockControllerLoader(json);

            DungeonMockController controller = dungeonLoader.loadController();
            return controller;
        } catch (Exception e) {
            return null;
        }

    }

    @Test
    public void ExitGoalTest1() {
        // checks if walls being loaded in crashes the game
        DungeonMockController controller = setup();
        assertNotEquals(null, controller);
        Dungeon dungeon = controller.dungeon;
        // check goal isnt complete
        assertFalse(dungeon.completedGoal());

        // move player onto exit
        controller.movePlayer(Direction.RIGHT);

        // check goal is now complete
        assertTrue(dungeon.completedGoal());
    }

    @Test
    public void playerMovesOnAndOffExit() {
        DungeonMockController controller = setup();
        
        Dungeon dungeon = controller.dungeon;

        // check goal isn't complete
        assertFalse(dungeon.completedGoal());

        // move player onto exit
        controller.movePlayer(Direction.RIGHT);

        // check goal is now complete
        assertTrue(dungeon.completedGoal());

        // move player off exit
        controller.movePlayer(Direction.LEFT);

        // check goal isnt complete
        assertFalse(dungeon.completedGoal());
    }

}