package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import unsw.dungeon.*;

public class PortalTest {
    
    public DungeonMockController setup1() {

        try {
            JSONObject json = new JSONObject();
            json.put("height", 1);
            json.put("width", 5);

            JSONArray entities = new JSONArray();

            JSONObject player = new JSONObject();
            player.put("x", 1);
            player.put("y", 0);
            player.put("type", "player");

            JSONObject portal1 = new JSONObject();
            portal1.put("type", "portal");
            portal1.put("id", 1);
            portal1.put("x", 4);
            portal1.put("y", 0);

            JSONObject portal2 = new JSONObject();
            portal2.put("type", "portal");
            portal2.put("id", 1);
            portal2.put("x", 0);
            portal2.put("y", 0);

            entities.put(player);
            entities.put(portal1);
            entities.put(portal2);

            json.put("entities", entities);

            JSONObject goal = new JSONObject();
            goal.put("goal", "boulders");
            
            json.put("goal-condition", goal);

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
            json.put("width", 3);

            JSONArray entities = new JSONArray();

            JSONObject player = new JSONObject();
            player.put("x", 1);
            player.put("y", 1);
            player.put("type", "player");

            JSONObject portal1 = new JSONObject();
            portal1.put("type", "portal");
            portal1.put("id", 0);
            portal1.put("x", 0);
            portal1.put("y", 0);

            JSONObject portal2 = new JSONObject();
            portal2.put("type", "portal");
            portal2.put("id", 0);
            portal2.put("x", 2);
            portal2.put("y", 2);

            JSONObject portal3 = new JSONObject();
            portal3.put("type", "portal");
            portal3.put("id", 1);
            portal3.put("x", 2);
            portal3.put("y", 0);

            JSONObject portal4 = new JSONObject();
            portal4.put("type", "portal");
            portal4.put("id", 1);
            portal4.put("x", 0);
            portal4.put("y", 2);

            entities.put(player);
            entities.put(portal1);
            entities.put(portal2);
            entities.put(portal3);
            entities.put(portal4);

            json.put("entities", entities);

            JSONObject goal = new JSONObject();
            goal.put("goal", "boulders");
            
            json.put("goal-condition", goal);

            DungeonMockControllerLoader dungeonLoader = new DungeonMockControllerLoader(json);

            DungeonMockController controller = dungeonLoader.loadController();
            return controller;
        } catch (Exception e) {
            return null;
        }
    }

    // case:
    // portals are properly linked

    @Test
    public void portalsAreLoaded() {
        DungeonMockController controller = setup1();

        assertNotEquals(null, controller);

        Dungeon dungeon = controller.dungeon;

        // check that first portal is loaded
        assertEquals(1, dungeon.getEntitiesOnTile(0, 0).size());
        assertTrue(dungeon.getEntitiesOnTile(0, 0).get(0) instanceof Portal);

        // check that second portal is loaded
        assertEquals(1, dungeon.getEntitiesOnTile(4, 0).size());
        assertTrue(dungeon.getEntitiesOnTile(4, 0).get(0) instanceof Portal);
    }

    @Test
    public void portalTeleportsPlayer() {
        DungeonMockController controller = setup1();

        Dungeon dungeon = controller.dungeon;

        // walk into the first portal
        dungeon.getPlayer().move(Direction.LEFT);
        
        // check the player was teleported
        assertEquals(3, dungeon.getPlayer().getX());
        assertEquals(0, dungeon.getPlayer().getY());

        // walk back into the portal
        dungeon.getPlayer().move(Direction.RIGHT);
        
        // check the player was teleported
        assertEquals(1, dungeon.getPlayer().getX());
        assertEquals(0, dungeon.getPlayer().getY());
    }

    @Test
    public void playerTeleportsToTheCorrectPortal() {
        DungeonMockController controller = setup2();

        Dungeon dungeon = controller.dungeon;

        // walk into the first portal
        dungeon.getPlayer().move(Direction.LEFT);
        dungeon.getPlayer().move(Direction.UP);

        // check that the player was teleported to the correct place
        assertEquals(2, dungeon.getPlayer().getX());
        assertEquals(1, dungeon.getPlayer().getY());

        // walk into the second portal
        dungeon.getPlayer().move(Direction.UP);

        // check that the player was teleported to the correct place
        assertEquals(0, dungeon.getPlayer().getX());
        assertEquals(1, dungeon.getPlayer().getY());
    }

}