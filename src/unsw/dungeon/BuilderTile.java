package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class BuilderTile {
    private List<BuilderEntity> entities;

    public BuilderTile() {
        this.entities = new ArrayList<BuilderEntity>();
    }

    public void add(BuilderEntity entity) {
        entities.add(entity);
    }

    public List<BuilderEntity> getEntities() {
        return this.entities;
    }

    public void remove() {
        // remove top element
        if (!entities.isEmpty())
            entities.remove(entities.size()-1);
    }
}