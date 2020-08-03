package unsw.dungeon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

public class Builder {

    private int height;
    private int width;
    private BuilderTile[][] tiles;
    private String goalString;
    
    // initial build
    // create the 2d array
    public Builder(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new BuilderTile[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                this.tiles[x][y] = new BuilderTile();
            }
        }
        this.goalString = "(exit)";
    }

    public void resize(int newWidth, int newHeight) {
        BuilderTile[][] newTiles = new BuilderTile[newWidth][newHeight];
        for (int y = 0; y < newHeight; y++) {
            for (int x = 0; x < newWidth; x++) {
                if (x < this.width && y < this.height) {
                    newTiles[x][y] = this.tiles[x][y];
                } else {
                    newTiles[x][y] = new BuilderTile();
                }
            }
        }
        this.tiles = newTiles;
        this.width = newWidth;
        this.height = newHeight;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return height;
    }

    public void addEntity(String type, int x, int y) {
        BuilderEntity toAdd = new BuilderEntity(type);
        tiles[x][y].add(toAdd);
    }

    public void addEntity(String type, int x, int y, int id) {
        BuilderEntity toAdd = new BuilderEntity(type, id);
        tiles[x][y].add(toAdd);
    }

    public void removeEntity(int x, int y) {
        tiles[x][y].remove();
    }

    public BuilderTile[][] getTiles() {
        return this.tiles;
    }

    public void setGoalString(String string) {
        this.goalString = string;
    }

    public JSONObject getJSON() {
        JSONObject root = new JSONObject();
        root.put("width", this.width);
        root.put("height", this.height);

        JSONArray entities = new JSONArray();

        // Organise the entities in order of what should appear first
        // i.e. wall appears above every other entity
        addEntitiesWithType(entities, "switch");
        addEntitiesWithType(entities, "treasure");
        addEntitiesWithType(entities, "phase");
        addEntitiesWithType(entities, "invincibility");
        addEntitiesWithType(entities, "sword");
        addEntitiesWithType(entities, "key");
        addEntitiesWithType(entities, "door");
        addEntitiesWithType(entities, "portal");
        addEntitiesWithType(entities, "exit");
        addEntitiesWithType(entities, "wizard");
        addEntitiesWithType(entities, "enemy");
        addEntitiesWithType(entities, "boulder");
        addEntitiesWithType(entities, "player");
        addEntitiesWithType(entities, "wall");


        root.put("entities", entities);
        root.put("goal-condition", getGoalJSON());
        return root;
    }

    private void addEntitiesWithType(JSONArray entities, String type) {
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                for (BuilderEntity e : this.tiles[x][y].getEntities()) {
                    if (e.getType().equals(type)) {
                        JSONObject toAdd = new JSONObject();
                        toAdd.put("x", x);
                        toAdd.put("y", y);
                        toAdd.put("type", e.getType());
                        if (e.hasId()) {
                            toAdd.put("id", e.getId());
                        }

                        entities.put(toAdd);
                    }
                }
            }
        }
    }

    public String getGoalString() {
        return this.goalString;
    }

    public void printGoalString() {
        System.out.println(parseGoalString(this.goalString).toString(2));
    }

    public JSONObject getGoalJSON() {
        return parseGoalString(this.goalString);
    }

    private int getFirstIndexOf(String string, String searchingFor) {
        int curr = 0;
        int index = -1;
        while (curr < string.length()) {
            String ch = Character.toString(string.charAt(curr));
            if (ch.equals(searchingFor)) {
                index = curr;
                break;
            }
            curr++;
        }

        return index;
    }

    private int findClosingBracketIndex(String string, int openingIndex) {
        int opens = 0;
        int curr = openingIndex + 1;
        while (curr < string.length()) {
            String ch = Character.toString(string.charAt(curr));
            if (ch.equals(")") && opens == 0) {
                return curr;
            } else if (ch.equals(")")) {
                opens--;
            } else if (ch.equals("(")) {
                opens++;
            }
            curr++;
        }
        return -1;
    }

    private JSONObject parseGoalString(String string) {
        if (string.contains("AND") || string.contains("OR")) {
            string = string.substring(1, string.length() - 1); // trim brackets
            JSONObject composite = new JSONObject();
            if (string.contains("AND")) {
                composite.put("goal", "AND");
            } else {
                composite.put("goal", "OR");
            }
            JSONArray subgoals = new JSONArray();

            int open = getFirstIndexOf(string, "(");
            while (open != -1) {
                int close = findClosingBracketIndex(string, open);
                subgoals.put(parseGoalString(string.substring(open, close + 1)));
                string = string.substring(close + 1);
                open = getFirstIndexOf(string, "(");
            }

            composite.put("subgoals", subgoals);
            return composite;
        } else {
            JSONObject leaf = new JSONObject();
            leaf.put("goal", string.replaceAll("\\(", "").replaceAll("\\)",""));
            return leaf;
        }
    }
}