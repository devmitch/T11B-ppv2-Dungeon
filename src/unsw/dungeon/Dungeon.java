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

    private DungeonController controller; // for milestone 3
    private int width, height;
    
    private List<Entity> entities;
    private Tile[][] tiles;

    private Player player;

    private Goal rootGoal;

    public Dungeon(int width, int height, Goal rootGoal) {
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
        this.controller = null;
        this.rootGoal = rootGoal;
    }

    /**
     * Notifies the dungeon if it is completed. (To be used in milestone 3). 
     */
    private void notifyIfDungeonCompleted() {
        if (completedGoal()) {
            System.out.println("Dungeon Completed!");
        }
    }

    /**
     * @return true if the goal for the dungeon is completed, false otherwise. (mainly used for
     * testing).
     */
    public boolean completedGoal() {
        return this.rootGoal.isSatisfied();
    }

    /**
     * Notifies the entities that are observing the player of its movement
     * all game state updates are done after a player moves (Observer Pattern)
     */
    public void updateObservers() {
        if (completedGoal()) return;
        List<Entity> entityList = new ArrayList<>(this.entities);
        for (Entity e : entityList) {
            if (e instanceof Enemy) {
                ((Enemy)e).makeMove();
            } else if (e instanceof Exit) {
                ((Exit)e).updateAtExitState();
            }
        }
        notifyIfDungeonCompleted();
    }

    public Tile[][] getTiles() {
        return this.tiles;
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

    
    /* Might need for iteration 3
    public void newEntityImage(Entity entity) {
        if (controller != null) {
            controller.newEntity(entity);
        }
    }
    
    public void setController(DungeonController controller) {
        this.controller = controller;
    }
    */

    /**
     * "Drops" the given entity onto the dungeon. 
     * 
     * @param entity the entity to place on the map.
     * @param x the x coordinate of the entity.
     * @param y the y coordinate of the entity.
     */
    public void dropEntity(Entity entity, int x, int y) {
        entity.enable();
        entity.setX(x);
        entity.setY(y);
        addEntity(entity);
    }

    /**
     * Adds the given entity to the dungeon (and to the tile that corresponds to its position).
     * 
     * @param entity the entity to add to the dungeon.
     */
    public void addEntity(Entity entity) {
        if (entity != null) {
            entities.add(entity);
            tiles[entity.getX()][entity.getY()].addEntityOnTile(entity);
        }
    }

    /**
     * Removes the given entity from the dungeon (and from the tile that holds it).
     * 
     * @param entity the entity to remove.
     */
    public void removeEntity(Entity entity) {
        if (entity != null) {
            entities.remove(entity);
            tiles[entity.getX()][entity.getY()].removeEntityOnTile(entity);
            entity.disable(); // triggers the event handler in the view
            if (entity instanceof Player) {
                this.player = null;
            }
        }
    }

    /**
     * Updates the coordinates of the entity and then moves the given entity to the tile given by
     * newX and newY. (newX and newY are assumed to be valid since this function is called from
     * movement).
     * 
     * @param entity the entity to move.
     * @param newX the new x coordinate.
     * @param newY the new y coordinate.
     */
    public void moveEntity(Entity entity, int newX, int newY) {

        // Remove the entity from the tile
        tiles[entity.getX()][entity.getY()].removeEntityOnTile(entity);

        // Update the position of the entity
        entity.setX(newX);
        entity.setY(newY);
        
        // Add the entity to its new tile
        tiles[entity.getX()][entity.getY()].addEntityOnTile(entity);
    }

    /**
     * Gets the entities that are on the tile which corresponds to the given x and y.
     * 
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @return a list of entities.
     */
    public List<Entity> getEntitiesOnTile(int x, int y) {
        if (x < 0 || x >= this.getWidth() || y < 0 || y >= this.getHeight()) {
            return null; // coordinates out of range
        }
        return tiles[x][y].getEntities();
    }

    /**
     * Returns true if the tile that corresponds to the given x and y coordinates is obstructed,
     * false otherwise.
     * 
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @return true if the tile is obstructed, false otherwise.
     */
    public boolean isTileObstructed(int x, int y) {
        return tiles[x][y].isObstructed();
    }

    /**
     * Checks whether the dungeon has this entity or not. Used for tests!
     */
    public boolean hasEntity(Entity entity) {
        return this.entities.contains(entity);
    }

    /**
     * Returns true if both entities are on the same tile, false otherwise.
     * 
     * @param entity1 the first entity.
     * @param entity2 the second entity.
     * @return true if they are on the same tile, false otherwise.
     */
    public boolean areEntitiesOnSameTile(Entity entity1, Entity entity2) {
        return tiles[entity1.getX()][entity1.getY()].hasEntity(entity2);
    }

    /**
     * If the player and the requestedEntity occupy the same tile, and the requestedEntity exists
     * within the dungeon it is removed from the dungeon and then returned.
     * 
     * @param player the player.
     * @param requestEntity the requested entity.
     * @return the requestedEntity or null. 
     */
    public Entity requestEntity(Player player, Entity requestEntity) {
        if (player.getX() == requestEntity.getX() && player.getY() == requestEntity.getY()) {
            if (entities.contains(requestEntity)) {
                removeEntity(requestEntity);
                return requestEntity;
            }
        }
        return null;
    }

    /**
     * Returns the first occurrence of a portal that has the same id as the given id. If no portal
     * exists null is returned.
     * 
     * @param id the id of the portal.
     * @return the portal or null.
     */
    public Portal getPortalWithId(int id) {
        for (Entity entity : entities) {
            if (entity instanceof Portal) {
                Portal p = (Portal) entity;
                if (p.getId() == id) {
                    return p;
                }
            }
        }
        return null;
    }
}
