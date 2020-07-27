package unsw.dungeon;

import org.json.JSONObject;

public class DungeonMockControllerLoader extends DungeonLoader {
    public DungeonMockControllerLoader(JSONObject json) {
        super(json);
    }


    // the following do nothing since the tests are running headless
    @Override
    public void onLoad(Entity player) {

    }

    @Override
    public void onLoad(Wall wall) {

    }

    @Override
    public void onLoad(Boulder boulder) {

    }

    @Override
    public void onLoad(Exit exit) {

    }

    @Override
    public void onLoad(Treasure treasure) {

    }

    @Override
    public void onLoad(Key key) {

    }

    @Override
    public void onLoad(Door door) {

    }

    @Override
    public void onLoad(FloorSwitch floorSwitch) {

    }

    @Override
    public void onLoad(Portal portal) {

    }

    @Override
    public void onLoad(Enemy enemy) {

    }

    @Override
    public void onLoad(Sword sword) {

    }

    @Override
    public void onLoad(InvincibilityPotion potion) {

    }

    @Override
    public void onLoad(PhasePotion potion) {
        
    }

    public DungeonMockController loadController() {
        return new DungeonMockController(load());
    }
}