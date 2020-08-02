package unsw.dungeon;

import javafx.beans.property.IntegerProperty;

public class BuilderEntity {
    private String type;
    private IntegerProperty x, y;
    private boolean hasId;
    private int id;

    public BuilderEntity(String type) {
        this.hasId = false;
        this.type = type;
    }

    public BuilderEntity(String type, int id) {
        this.hasId = true;
        this.type = type;
        this.id = id;
    }

    public boolean hasId() {
        return this.hasId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public String getType() {
        return type;
    }

    public IntegerProperty x() {
        return this.x;
    }

    public IntegerProperty y() {
        return this.y;
    }
}