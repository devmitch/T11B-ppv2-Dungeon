package unsw.dungeon;

public class ClosedDoorState implements DoorState {
    public boolean isObstruction() {
        return true;
    }

    public boolean isInteractable() {
        return true;
    }
}