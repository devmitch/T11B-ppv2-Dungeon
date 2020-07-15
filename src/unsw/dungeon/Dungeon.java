/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private Tile[][] tiles;
    private Player player;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.tiles = new Tile[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                this.tiles[x][y] = new Tile(x, y);
            }
        }
        this.player = null;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntity(Entity entity) {
        if (entity != null) {
            entities.add(entity);
            tiles[entity.getX()][entity.getY()].addEntity(entity);
        }
    }

    public List<Entity> getEntitiesOnTile(int x, int y) {
        /*List<Entity> entitiesOnTiles = new ArrayList<>();

        for (Entity entity : entities) {
            // this is very ugly 
            if (entity != null && entity.getX() == x && entity.getY() == y) {
                entitiesOnTiles.add(entity);
            }
        }

        return entitiesOnTiles;*/

        return tiles[x][y].getEntities();
    }
}
