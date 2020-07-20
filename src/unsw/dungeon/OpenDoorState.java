package unsw.dungeon;

public class OpenDoorState implements DoorState {
    public boolean isObstruction() {
        return false;
    }

    public boolean isInteractable() {
        return false;
    }
}