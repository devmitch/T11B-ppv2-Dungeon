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

    /**
     * @return the list of entities that are on this tile.
     */
    public List<Entity> getEntities() {
        return this.entities;
    }

    /**
     * Adds the given entity e to the tile.
     * 
     * @param e the entity to add.
     */
    public void addEntityOnTile(Entity e) {
        if (!this.entities.contains(e)) {
            this.entities.add(e);
        }
    }

    /**
     * Removes the given entity e from the tile.
     * 
     * @param e the entity to remove.
     */
    public void removeEntityOnTile(Entity e) {
        this.entities.remove(e);
    }

    /**
     * @return true if the tile is obstructed, false otherwise.
     */
    public boolean isObstructed() {
        for (Entity e : this.entities) {
            if (e.isObstruction()) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param e the entity to check.
     * @return true if the entity e is on the tile, false otherwise. 
     */
    public boolean hasEntity(Entity e) {
        return entities.contains(e);
    }
}