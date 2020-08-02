package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Test;

import unsw.dungeon.*;

public class WizardTest {
    
    public DungeonMockController setup1() {
        
        try {
            JSONObject json = new JSONObject();
            json.put("height", 3);
            json.put("width", 4);

            JSONArray entities = new JSONArray();

            JSONObject player = new JSONObject();
            player.put("x", 0);
            player.put("y", 1);
            player.put("type", "player");

            JSONObject wall1 = new JSONObject();
            wall1.put("type", "wall");
            wall1.put("x", 1);
            wall1.put("y", 0);

            JSONObject wall2 = new JSONObject();
            wall2.put("type", "wall");
            wall2.put("x", 1);
            wall2.put("y", 1);

            JSONObject wall3 = new JSONObject();
            wall3.put("type", "wall");
            wall3.put("x", 1);
            wall3.put("y", 2);

            JSONObject wizard = new JSONObject();
            wizard.put("type", "wizard");
            wizard.put("x", 2);
            wizard.put("y", 1);

            entities.put(player);
            entities.put(wall1);
            entities.put(wall2);
            entities.put(wall3);
            entities.put(wizard);

            json.put("entities", entities);

            JSONObject goal = new JSONObject();
            goal.put("goal", "enemies");
            
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
            json.put("height", 3);
            json.put("width", 4);

            JSONArray entities = new JSONArray();

            JSONObject player = new JSONObject();
            player.put("x", 0);
            player.put("y", 1);
            player.put("type", "player");

            JSONObject wall1 = new JSONObject();
            wall1.put("type", "wall");
            wall1.put("x", 2);
            wall1.put("y", 0);

            JSONObject wall2 = new JSONObject();
            wall2.put("type", "wall");
            wall2.put("x", 1);
            wall2.put("y", 1);

            JSONObject wall3 = new JSONObject();
            wall3.put("type", "wall");
            wall3.put("x", 2);
            wall3.put("y", 2);

            JSONObject wall4 = new JSONObject();
            wall4.put("type", "wall");
            wall4.put("x", 3);
            wall4.put("y", 1);

            JSONObject wizard = new JSONObject();
            wizard.put("type", "wizard");
            wizard.put("x", 2);
            wizard.put("y", 1);

            entities.put(player);
            entities.put(wall1);
            entities.put(wall2);
            entities.put(wall3);
            entities.put(wizard);

            json.put("entities", entities);

            JSONObject goal = new JSONObject();
            goal.put("goal", "enemies");
            
            json.put("goal-condition", goal);

            DungeonMockControllerLoader dungeonLoader = new DungeonMockControllerLoader(json);

            DungeonMockController controller = dungeonLoader.loadController();
            return controller;
        } catch (Exception e) {
            return null;
        }
    }

    @Test
    public void wizardIsLoadedIntoTheGame() {
        DungeonMockController controller = setup1();

        // check that everything loaded with no exceptions
        assertNotNull(controller);

        // check that the wizard is on the expected tile
        assertEquals(1, controller.dungeon.getEntitiesOnTile(2, 1).size());
        assertTrue(controller.dungeon.getEntitiesOnTile(2, 1).get(0) instanceof Wizard);
    }

    @Test
    public void wizardSpawnsAnEnemyEveryTenSteps() {
        DungeonMockController controller = setup1();

        // move the player 9 times
        for (int i = 0; i < 9; i++) {
            controller.movePlayer(Direction.RIGHT);
        }

        // check that the wizard has not spawned an enemy yet
        assertEquals(0, controller.dungeon.getEntitiesOnTile(2, 0).size());
        assertEquals(0, controller.dungeon.getEntitiesOnTile(2, 2).size());
        assertEquals(0, controller.dungeon.getEntitiesOnTile(3, 1).size());

        // move the player and check again
        controller.movePlayer(Direction.RIGHT);
        assertEquals(1, controller.dungeon.getEntitiesOnTile(2, 0).size()
                        + controller.dungeon.getEntitiesOnTile(2, 2).size()
                        + controller.dungeon.getEntitiesOnTile(3, 1).size());

        // move the player 9 times
        for (int i = 0; i < 9; i++) {
            controller.movePlayer(Direction.RIGHT);
        }

        // check that the wizard has not spawned an enemy yet
        assertEquals(1, controller.dungeon.getEntitiesOnTile(2, 0).size()
                        + controller.dungeon.getEntitiesOnTile(2, 2).size()
                        + controller.dungeon.getEntitiesOnTile(3, 1).size());

        // move the player and check again
        controller.movePlayer(Direction.RIGHT);
        assertEquals(2, controller.dungeon.getEntitiesOnTile(2, 0).size()
                        + controller.dungeon.getEntitiesOnTile(2, 2).size()
                        + controller.dungeon.getEntitiesOnTile(3, 1).size());

    }

    @Test
    public void wizardCannotSpawnEnemiesWhenTheTilesAreBlocked() {
        DungeonMockController controller = setup2();

        // move the player 30 times
        for (int i = 0; i < 30; i++) {
            controller.movePlayer(Direction.RIGHT);
        }

        // check that the wizard has not spawned an enemy yet
        assertEquals(1, controller.dungeon.getEntitiesOnTile(2, 0).size());
        assertEquals(1, controller.dungeon.getEntitiesOnTile(1, 1).size());
        assertEquals(1, controller.dungeon.getEntitiesOnTile(3, 1).size());
        assertEquals(1, controller.dungeon.getEntitiesOnTile(2, 2).size());
        assertEquals(1, controller.dungeon.getEntitiesOnTile(2, 1).size());

        
        // no enemies escaped and killed the player
        assertTrue(controller.dungeon.getEntitiesOnTile(0, 1).get(0) instanceof Player);

    }

    @Test
    public void wizardCanOnlySpawnTwoEnemies() {
        DungeonMockController controller = setup1();

        // move the player 30 times
        for (int i = 0; i < 30; i++) {
            controller.movePlayer(Direction.RIGHT);
        }

        // check that the wizard has not spawned more than 2 enemies
        assertEquals(2, controller.dungeon.getEntitiesOnTile(2, 0).size()
                        + controller.dungeon.getEntitiesOnTile(2, 2).size()
                        + controller.dungeon.getEntitiesOnTile(3, 1).size()
                        + controller.dungeon.getEntitiesOnTile(3, 0).size()
                        + controller.dungeon.getEntitiesOnTile(3, 2).size());
    }

    // Cases:
    //  - The spawned enemy is not tracked by the enemy goal (could be changed so account for that
    //    in the test).
    //  -  Idk what else

    @Test
    public void wizardSpawnsAnEnemyIfOneHasDied() {
        DungeonMockController controller = setup1();

        // move the player 30 times
        for (int i = 0; i < 30; i++) {
            controller.movePlayer(Direction.RIGHT);
        }

        // check that the wizard is in the expected place
        assertTrue(controller.dungeon.getEntitiesOnTile(2, 1).get(0) instanceof Wizard);

        // kill an enemy that was spawned in
        Enemy enemy = (Enemy)controller.dungeon.getEntitiesOnTile(2, 0).get(0);
        assertFalse(enemy instanceof Wizard);
        enemy.die();

        // check that there is only 1 enemy remaining
        assertEquals(1, controller.dungeon.getEntitiesOnTile(2, 0).size()
                        + controller.dungeon.getEntitiesOnTile(2, 2).size()
                        + controller.dungeon.getEntitiesOnTile(3, 1).size());
        
        // move again and then check if 1 has spawned in
        // enemy is expected to spawn in after the tenth move
        for (int i = 0; i < 9; i++) {
            controller.movePlayer(Direction.RIGHT);
        }

        assertEquals(1, controller.dungeon.getEntitiesOnTile(2, 0).size()
                        + controller.dungeon.getEntitiesOnTile(2, 2).size()
                        + controller.dungeon.getEntitiesOnTile(3, 1).size());

        // move and the enemy is expected to spawn in
        controller.movePlayer(Direction.RIGHT);
        assertEquals(2, controller.dungeon.getEntitiesOnTile(2, 0).size()
                        + controller.dungeon.getEntitiesOnTile(2, 2).size()
                        + controller.dungeon.getEntitiesOnTile(3, 1).size());
    }

    @Test
    public void killingSpawnedEnemiesDoesntGoTowardsEnemyGoal() {
        DungeonMockController controller = setup1();

        // move the player 30 times
        for (int i = 0; i < 30; i++) {
            controller.movePlayer(Direction.RIGHT);
        }

        // check that the wizard is in the expected place
        assertTrue(controller.dungeon.getEntitiesOnTile(2, 1).get(0) instanceof Wizard);

        assertFalse(controller.dungeon.completedGoal());

        // kill an enemy that was spawned in
        Enemy enemy = (Enemy)controller.dungeon.getEntitiesOnTile(2, 0).get(0);
        assertFalse(enemy instanceof Wizard);
        enemy.die();

        assertFalse(controller.dungeon.completedGoal());

        // check that there is only 1 enemy remaining
        assertEquals(1, controller.dungeon.getEntitiesOnTile(2, 0).size()
                        + controller.dungeon.getEntitiesOnTile(2, 2).size()
                        + controller.dungeon.getEntitiesOnTile(3, 1).size());

        assertFalse(controller.dungeon.completedGoal());

        // move again and then check if 1 has spawned in
        // enemy is expected to spawn in after the tenth move
        for (int i = 0; i < 9; i++) {
            controller.movePlayer(Direction.RIGHT);
        }

        assertEquals(1, controller.dungeon.getEntitiesOnTile(2, 0).size()
                        + controller.dungeon.getEntitiesOnTile(2, 2).size()
                        + controller.dungeon.getEntitiesOnTile(3, 1).size());

        // move and the enemy is expected to spawn in
        controller.movePlayer(Direction.RIGHT);
        assertEquals(2, controller.dungeon.getEntitiesOnTile(2, 0).size()
                        + controller.dungeon.getEntitiesOnTile(2, 2).size()
                        + controller.dungeon.getEntitiesOnTile(3, 1).size());

        assertFalse(controller.dungeon.completedGoal());

        // kill the wizard and the goal is completed
        Wizard wizard = (Wizard)controller.dungeon.getEntitiesOnTile(2, 1).get(0);
        assertTrue(wizard instanceof Wizard);
        wizard.die();

        assertTrue(controller.dungeon.completedGoal());
    }

}