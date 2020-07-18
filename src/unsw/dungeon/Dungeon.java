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

    private DungeonController controller;
    private int width, height;
    
    private List<Entity> entities;
    private Tile[][] tiles;

    private Player player;

    private Goal rootGoal;
    private boolean isComplete;

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
        this.isComplete = false;
    }

    private void updateTree() {
        if (this.rootGoal.isSatisfied()) {
            this.isComplete = true;
            System.out.println("Game over!");
        }
    }

    // "obervers" are just specific entities
    public void updateObservers() {
        for (Entity e : this.entities) {
            if (e instanceof Enemy) {
                ((Enemy)e).makeMove();
                // if enemy is deleted from entities list, recurse
                // stops concurrent modification to list error
                if (!this.entities.contains(player)) {
                    break;
                } else if (!this.entities.contains(e)) {
                    updateObservers();
                    break;
                }
            } else if (e instanceof Exit) {
                ((Exit)e).updateAtExitState();
            }
        }
        updateTree();
    }

    public Tile[][] getTiles() {
        return this.tiles;
    }

    public void setController(DungeonController controller) {
        this.controller = controller;
    }

    // after a player moves
    public void updateMap() {
        for (Entity entity : entities) {
            if (entity instanceof Enemy) {
                ((Enemy)entity).makeMove();
            }
        }
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

    public void newEntityImage(Entity entity) {
        if (controller != null) {
            controller.newEntity(entity);
        }
    }

    public void dropEntity(Entity entity, int x, int y) {
        entity.enable();
        entity.setX(x);
        entity.setY(y);
        addEntity(entity);
        newEntityImage(entity);
    }

    // for an entity that already has an ImageView
    public void addEntity(Entity entity) {
        if (entity != null) {
            entities.add(entity);
            tiles[entity.getX()][entity.getY()].addEntityOnTile(entity);
        }
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
        tiles[entity.getX()][entity.getY()].removeEntityOnTile(entity);
        entity.disable(); //triggers the event handler in the view
    }

    public void moveEntity(Entity entity, int newX, int newY) {

        // Remove the entity from the tile
        tiles[entity.getX()][entity.getY()].removeEntityOnTile(entity);

        // Update the position of the entity
        entity.setX(newX);
        entity.setY(newY);
        
        // Add the entity to its new tile
        tiles[entity.getX()][entity.getY()].addEntityOnTile(entity);
    }

    public List<Entity> getEntitiesOnTile(int x, int y) {
        return tiles[x][y].getEntities();
    }

    /**
     * Returns true if the entity e is on the same tile as entity target, false otherwise.
     * 
     * @param e
     * @param target
     * @return
     */
    public boolean areEntitiesOnSameTile(Entity e, Entity target) {
        for (Entity entity : getEntitiesOnTile(e.getX(), e.getY())) {
            if (target == entity) { //  we want the _exact_ same object. 
                return true;
            }
        }
        return false;
    }

    public Entity requestEntity(Player requestor, Entity request) {
         if (requestor.getX() == request.getX() && requestor.getY() == request.getY()) {
             if (entities.contains(request)) {
                 removeEntity(request);
                 return request;
             }
         }
         return null;
    }

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
