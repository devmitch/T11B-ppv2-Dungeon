package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;


public class Builder {


    private int height;
    private int width;
    private BuilderTile[][] tiles;
    
    // initial build
    // create the 2d array
    public Builder() {
        height = 10;
        width = 10;
        this.tiles = new BuilderTile[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                this.tiles[x][y] = new BuilderTile();
            }
        }
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return height;
    }

    public void addEntity(String type, int x, int y) {
        BuilderEntity toAdd = new BuilderEntity(type);
        tiles[x][y].add(toAdd);
    }

    public void addEntity(String type, int x, int y, int id) {
        BuilderEntity toAdd = new BuilderEntity(type, id);
        tiles[x][y].add(toAdd);
    }

    public void removeEntity(int x, int y) {
        tiles[x][y].remove();
    }
    
    // setDungeonSize(int width, int height)

    // addEntity(JSONObject json, int x, int y)

    // removeEntity(int x, int y)
    // removes top entity on tile


}