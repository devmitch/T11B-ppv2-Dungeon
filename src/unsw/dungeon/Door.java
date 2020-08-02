package unsw.dungeon;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;

public class Door extends Entity {

    private ObjectProperty<Image> displayImage;

    private Image openDoorImage;

    private DoorState currentState;
    private int id;

    public Door(Dungeon dungeon, int x, int y, int id) {
        super(dungeon, x, y, true, true, false);
        this.id = id;
        this.currentState = new ClosedDoorState();
        displayImage = new SimpleObjectProperty<Image>();
    }

    @Override
    public void interactWith(Entity e, Direction d) {
        if (e instanceof Player) {
            Player p = (Player) e;
            Key key = p.useKey(this.id);
            if (key != null) {
                this.currentState = new OpenDoorState();
                displayImage.set(openDoorImage);
            } else if (p.isInvisible()) {
                int targetX = getX() + d.getXOffset();
                int targetY = getY() + d.getYOffset();
                if (!dungeon.isTileObstructed(targetX, targetY)) {
                    dungeon.moveEntity(p, targetX, targetY);
                    p.stun(1);
                }
            }
        }
    }

    @Override
    public boolean isInteractable() {
        return this.currentState.isInteractable();
    }

    @Override
    public boolean isObstruction() {
        return this.currentState.isObstruction();
    }

    public void setOpenDoorImage(Image openDoorImage) {
        this.openDoorImage = openDoorImage;
    }

    public void setClosedDoorImage(Image closedDoorImage) {
        displayImage.set(closedDoorImage);
    }

    public ObservableValue<Image> getImageProperty() {
        return displayImage;
    }

}