package test;

import org.json.JSONArray;
import org.json.JSONObject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;


public class KeyDoorTest {

    public DungeonMockController setup() {
        try {
            JSONObject json = new JSONObject();
            json.put("width", 3);
            json.put("height", 3);
            JSONArray entities = new JSONArray();

            JSONObject player = new JSONObject();
            player.put("x", 1);
            player.put("y", 1);
            player.put("type", "player");
            entities.put(player);

            JSONObject key1 = new JSONObject();
            key1.put("x", 1);
            key1.put("y", 0);
            key1.put("type", "key");
            key1.put("id", 1);
            entities.put(key1);

            JSONObject key2 = new JSONObject();
            key2.put("x", 0);
            key2.put("y", 1);
            key2.put("type", "key");
            key2.put("id", 2);
            entities.put(key2);

            JSONObject door1 = new JSONObject();
            door1.put("x", 1);
            door1.put("y", 2);
            door1.put("type", "door");
            door1.put("id", 1);
            entities.put(door1);


            JSONObject door2 = new JSONObject();
            door2.put("x", 2);
            door2.put("y", 1);
            door2.put("type", "door");
            door2.put("id", 2);
            entities.put(door2);

            // so we dont get game over
            JSONObject switch1 = new JSONObject();
            switch1.put("x", 2);
            switch1.put("y", 2);
            switch1.put("type", "switch");
            entities.put(switch1);

            json.put("entities", entities);

            JSONObject goal = new JSONObject();
            goal.put("goal", "boulders");
            json.put("goal-condition", goal);

            DungeonMockControllerLoader dungeonLoader = new DungeonMockControllerLoader(json);

            DungeonMockController controller = dungeonLoader.loadController();
            return controller;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    // Two keys, two doors
    @Test
    public void KeyDoorTest1() {
        System.out.println("starting KeyDoorTest1");
        DungeonMockController controller = setup();
        assertNotEquals(controller, null);
        Dungeon dungeon = controller.dungeon;

        // grab key1
        controller.movePlayer(Direction.UP);
        // check the player successfully gets key 1
        assertEquals(dungeon.getPlayer().getKey().getId(), 1);
        controller.movePlayer(Direction.DOWN);

        // try to enter door2 (should not work, id mismatch between key/door)
        controller.movePlayer(Direction.RIGHT);
        // check that player doesn't move
        assertEquals(dungeon.getPlayer().getX(), 1);
        assertEquals(dungeon.getPlayer().getY(), 1);
        // check that door remains obstruction (closed)
        Entity door2Entity = dungeon.getEntitiesOnTile(2, 1).get(0);
        assertTrue(door2Entity instanceof Door);
        Door door2 = (Door) door2Entity;
        assertTrue(door2.isObstruction());

        // swap out key1 for key2
        controller.movePlayer(Direction.LEFT);
        // check that player now has key2
        assertEquals(dungeon.getPlayer().getKey().getId(), 2);
        controller.movePlayer(Direction.RIGHT);
        // check that key1 is now on ground
        Entity key1Entity = dungeon.getEntitiesOnTile(0, 1).get(0);
        assertTrue(key1Entity instanceof Key);
        Key key1 = (Key) key1Entity;
        assertEquals(key1.getId(), 1);

        // attempt to open door 2 again (should work)
        controller.movePlayer(Direction.RIGHT);
        // check the player is now on the door2 tile
        assertEquals(dungeon.getPlayer().getX(), 2);
        assertEquals(dungeon.getPlayer().getY(), 1);
        // check that the door is no longer an obstruction
        controller.movePlayer(Direction.LEFT);
        assertTrue(!door2.isObstruction());
        // check that the player no longer has key 2
        assertTrue(dungeon.getPlayer().getKey() == null);

        // get key 1
        controller.movePlayer(Direction.LEFT);
        controller.movePlayer(Direction.RIGHT);
        // check player has key 1
        assertEquals(dungeon.getPlayer().getKey().getId(), 1);
        // check that key1 is no longer on ground
        assertEquals(dungeon.getEntitiesOnTile(0, 1).size(), 0);

        // attempt to open door 1
        controller.movePlayer(Direction.DOWN);
        // check the player is on door 1 tile
        assertEquals(dungeon.getPlayer().getX(), 1);
        assertEquals(dungeon.getPlayer().getY(), 2);
        // check that the door is no longer an obstruction
        controller.movePlayer(Direction.UP);
        Entity door1Entity = dungeon.getEntitiesOnTile(1, 2).get(0);
        assertTrue(door1Entity instanceof Door);
        Door door1 = (Door) door1Entity;
        assertTrue(!door1.isObstruction());
        // check that the player no longer has key 2
        assertTrue(dungeon.getPlayer().getKey() == null);
    }
}