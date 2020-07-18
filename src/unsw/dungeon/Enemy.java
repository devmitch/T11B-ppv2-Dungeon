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
        Tile toAdd = tiles[t.getX()+x][t.getY() + y];
        if (t.getX() + x >= 0 && t.getX() + x <= dungeon.getWidth()) {
            if (t.getY() + y >= 0 && t.getY() + y <= dungeon.getHeight()) {
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

        Tile curr = discovered[dungeon.getPlayer().getX()][dungeon.getPlayer().getY()];
        Tile prev = curr;
        while (curr != root) {
            prev = curr;
            curr = discovered[curr.getX()][curr.getY()];
        }
        movement.moveInDirection(directionToTile(prev));
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