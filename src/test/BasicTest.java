package test;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import unsw.dungeon.*;


public class BasicTest {

    public DungeonMockController setup() {
        try {
            System.out.println("starting try...");
            JSONObject json = new JSONObject(new JSONTokener(new FileReader("dungeons/itemtest.json")));
            System.out.println("passed loading json");
            DungeonMockControllerLoader dungeonLoader = new DungeonMockControllerLoader(json);

            DungeonMockController controller = dungeonLoader.loadController();
            return controller;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Test
    public void test1() {
        System.out.println("starting test1");
        DungeonMockController controller = setup();
        if (controller == null) {
            System.out.println("bro its null");
        }
        assertNotEquals(controller, null);
        Dungeon dungeon = controller.dungeon;
        System.out.println(dungeon.getPlayer().getY());
        controller.movePlayer(Direction.DOWN);
        System.out.println(dungeon.getPlayer().getY());
    }
}