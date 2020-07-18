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

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public List<Entity> getEntities() {
        return this.entities;
    }

    public void addEntityOnTile(Entity e) {
        this.entities.add(e);
    }

    public void removeEntityOnTile(Entity e) {
        this.entities.remove(e);
    }

    public boolean hasObstructable() {
        for (Entity e : this.entities) {
            if (e.isObstruction()) {
                return true;
            }
        }
        return false;
    }
}