package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;

    private TreasureGoalType treasureGoalType;
    private EnemyGoalType enemyGoalType;
    private ExitGoalType exitGoalType;
    private SwitchGoalType switchGoalType;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
        this.treasureGoalType = null;
        this.enemyGoalType = null;
        this.exitGoalType = null;
        this.switchGoalType = null;
    }

    // constructor for testing
    public DungeonLoader(JSONObject json) {
        this.json = json;
        this.treasureGoalType = null;
        this.enemyGoalType = null;
        this.exitGoalType = null;
        this.switchGoalType = null;
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        // create 4 general goals and package into list
        createGoals();

        // parse goals to create tree
        Goal rootGoal = parseGoals(json.getJSONObject("goal-condition"));

        Dungeon dungeon = new Dungeon(width, height, rootGoal);   

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        return dungeon;
    }

    private void createGoals() {
        this.treasureGoalType = new TreasureGoalType();
        this.enemyGoalType = new EnemyGoalType();
        this.exitGoalType = new ExitGoalType();
        this.switchGoalType = new SwitchGoalType();
    }

    private Goal parseGoals(JSONObject json) {
        
        String goalType = json.getString("goal");
        // composite case
        if (goalType.equals("AND") || goalType.equals("OR")) {

            CompositeGoal goal = new CompositeGoal(goalType);

            JSONArray subGoals = json.getJSONArray("subgoals");
            for (int i = 0; i < subGoals.length(); i++) {
                goal.addGoal(parseGoals(subGoals.getJSONObject(i)));
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

    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(dungeon, x, y);
            onLoad(wall);
            entity = wall;
            break;
        case "boulder":
            Boulder boulder = new Boulder(dungeon, x, y);
            onLoad(boulder);
            entity = boulder;
            break;
        case "exit":
            Exit exit = new Exit(dungeon, x, y, exitGoalType);
            onLoad(exit);
            entity = exit;
            break;
        case "treasure":
            Treasure treasure = new Treasure(dungeon, x, y);
            treasure.attach(treasureGoalType);
            onLoad(treasure);
            entity = treasure;
            break;
        case "key":
            Key key = new Key(dungeon, x, y, json.getInt("id"));
            onLoad(key);
            entity = key;
            break;
        case "door":
            Door door = new Door(dungeon, x, y, json.getInt("id"));
            onLoad(door);
            entity = door;
            break;
        case "switch":
            FloorSwitch floorSwitch = new FloorSwitch(dungeon, x, y);
            floorSwitch.attach(switchGoalType);
            onLoad(floorSwitch);
            entity = floorSwitch;
            break;
        case "portal":
            Portal portal = new Portal(dungeon, x, y, json.getInt("id"));
            onLoad(portal);
            entity = portal;
            break;
        case "enemy":
            Enemy enemy = new Enemy(dungeon, x, y, enemyGoalType);
            onLoad(enemy);
            entity = enemy;
            break;
        case "sword":
            Sword sword = new Sword(dungeon, x, y);
            onLoad(sword);
            entity = sword;
            break;
        case "invincibility":
            InvincibilityPotion potion = new InvincibilityPotion(dungeon, x, y);
            onLoad(potion);
            entity = potion;
            break;
        }
        dungeon.addEntity(entity);
    }

    public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);

    public abstract void onLoad(Boulder boulder);

    public abstract void onLoad(Exit exit);

    public abstract void onLoad(Treasure treasure);

    public abstract void onLoad(Key key);

    public abstract void onLoad(FloorSwitch floorSwitch);

    public abstract void onLoad(Portal portal);

    public abstract void onLoad(Enemy enemy);

    public abstract void onLoad(Sword sword);

    public abstract void onLoad(InvincibilityPotion potion);

    public abstract void onLoad(Door door);

}
