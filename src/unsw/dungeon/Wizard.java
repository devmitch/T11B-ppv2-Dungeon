package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * Every x amount of steps that the player is close to the wizard, it attempts
 * to spawn an enemy in a surround tile. A maximum of two enemies can only be spawned at a time.
 */
public class Wizard extends Enemy {

    private final int maxSpawnInterval = 10;
    private final int maxChildren = 2;

    private int stepsTillNextSpawn = 0;
    private List<Enemy> children;

    public Wizard(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
        children = new ArrayList<>();
    }
    
    @Override
    public void makeMove() {
        super.makeMove();

        if (!isDead()) {
            stepsTillNextSpawn++;
            if (stepsTillNextSpawn >= maxSpawnInterval) {
                // choose a tile to spawn an enemy in
                // and the spawn an enemy in it
                
                List<Tile> adjacentTiles = getAdjacentTiles(getTile());

                removeDeadChildren();
                if (adjacentTiles.size() != 0 && canSpawnEnemies()) {
                    // choose the first tile and add an enemy to it
                    Tile tile = adjacentTiles.get(0);
                    children.add(dungeon.spawnNewEnemy(tile));
                }

                stepsTillNextSpawn = 0;
            }
        }
    }

    private boolean canSpawnEnemies() {
        return children.size() < maxChildren;
    }

    private void removeDeadChildren() {
        children.removeIf(e -> e.isDead());
    }

}