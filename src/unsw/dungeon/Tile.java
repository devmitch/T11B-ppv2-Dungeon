package unsw.dungeon;

import java.util.List;
import java.util.ArrayList;


public class Tile {
    private List<Entity> entities;
    private int x;
    private int y;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        this.entities = new ArrayList<Entity>();
    }

    public List<Entity> getEntities() {
        return this.entities;
    }

    public void addEntity(Entity e) {
        this.entities.add(e);
    }
}