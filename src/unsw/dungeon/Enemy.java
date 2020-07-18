package unsw.dungeon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.List;
import java.util.ArrayList;

public class Enemy extends Entity {

    private Movement movement;

    public Enemy(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y, true, true, false);
        this.movement = new Movement(dungeon, this);
    }
    
    @Override
    public void interactWith(Entity e, Direction D) {
        if (e instanceof Player) {
            ((Player)e).duel(this);
        }
    }

    private void checkTile(Tile t, int x, int y, List<Tile> ret) {
        Tile[][] tiles = dungeon.getTiles();
        if (t.getX() + x >= 0 && t.getX() + x < dungeon.getWidth()) {
            if (t.getY() + y >= 0 && t.getY() + y < dungeon.getHeight()) {
                Tile toAdd = tiles[t.getX()+x][t.getY() + y];
                if (!toAdd.hasObstructable() || toAdd.getEntities().contains(dungeon.getPlayer())) {
                    ret.add(toAdd);
                }
            }
        }
    }

    private List<Tile> getAdjacentTiles(Tile t) {
        List<Tile> ret = new ArrayList<>();
        checkTile(t, 0, 1, ret);
        checkTile(t, 0, -1, ret);
        checkTile(t, 1, 0, ret);
        checkTile(t, -1, 0, ret);
        return ret;
    }

    public void makeMove() {
        if (dungeon.getPlayer().isInvincible()) {
            //run the fuck away
        } else {
            Direction onPath = pathFindBFS();
            if (onPath != Direction.NONE) {
                // move on path found
                movement.moveInDirection(onPath);
            } else {
                // no path found - try to move closer to player
                movement.moveInDirection(moveCloser());
            }
        }
    }

    private Direction moveCloser() {
        Tile[][] tiles = dungeon.getTiles();
        Tile enemy = tiles[getX()][getY()];
        Tile player = tiles[dungeon.getPlayer().getX()][dungeon.getPlayer().getY()];
        for (Tile adj : getAdjacentTiles(enemy)) {
            if (distanceBetween(player, adj) < distanceBetween(player, enemy)) {
                return directionToTile(adj);
            }
        }
        return Direction.NONE;
    }

    private double distanceBetween(Tile a, Tile b) {
        return Math.sqrt((a.getX() - b.getX())*(a.getX() - b.getX()) + (a.getY() - b.getY())*(a.getY() - b.getY()));
    }

    private Direction pathFindBFS() {
        // attempts to find path to player
        Tile[][] discovered = new Tile[dungeon.getWidth()][dungeon.getHeight()];
        for (int y = 0; y < dungeon.getHeight(); y++) {
            for (int x = 0; x < dungeon.getWidth(); x++) {
                discovered[x][y] = null;
            }
        }

        Tile[][] tiles = dungeon.getTiles();
        Queue<Tile> q = new LinkedList<>();
        Tile root = tiles[getX()][getY()];
        discovered[getX()][getY()] = root;
        q.add(root);
        while (!q.isEmpty()) {
            Tile t = q.remove();
            if (t.getEntities().contains(dungeon.getPlayer())) {
                // we are done - player is found
                break;
            } else {
                for (Tile adj : getAdjacentTiles(t)) {
                    if (discovered[adj.getX()][adj.getY()] == null) {
                        discovered[adj.getX()][adj.getY()] = t;
                        q.add(adj);
                    }
                }
            }
        }

        if (discovered[dungeon.getPlayer().getX()][dungeon.getPlayer().getY()] == null) {
            // if no path to player
            return Direction.NONE;
        }

        Tile curr = tiles[dungeon.getPlayer().getX()][dungeon.getPlayer().getY()];
        Tile prev = curr;
        while (curr != root) {
            prev = curr;
            curr = discovered[curr.getX()][curr.getY()];
        }
        return directionToTile(prev);
    }

    // assumes tiles are adjacent
    private Direction directionToTile(Tile t) {
        if (getX() < t.getX()) {
            return Direction.RIGHT;
        } else if (getX() > t.getX()) {
            return Direction.LEFT;
        } else if (getY() < t.getY()) {
            return Direction.DOWN;
        } else if (getY() > t.getY()) {
            return Direction.UP;
        } else {
            return Direction.NONE;
        }
    }
}