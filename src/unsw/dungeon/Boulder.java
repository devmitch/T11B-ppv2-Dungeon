package unsw.dungeon;

import javax.print.attribute.standard.DialogOwner;

public class Boulder extends Entity {
    private Dungeon dungeon;
    private Movement movement;

    public Boulder(Dungeon dungeon, int x, int y) {
        super(x, y, true);
        this.dungeon = dungeon;
        this.movement = new Movement(dungeon, this);
    }

    public void moveUp() {
        movement.moveDown();
    }

    public void moveDown() {
        movement.moveDown();
    }

    public void moveLeft() {
        movement.moveLeft();
    }

    public void moveRight() {
        movement.moveRight();
    }

    public void move(Direction D) {
        movement.move(D);
    }

}