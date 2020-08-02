package unsw.dungeon;

public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT,
    NONE;

    private int xOffset;
    private int yOffset;

    static {
        UP.xOffset = 0;
        UP.yOffset = -1;
        DOWN.xOffset = 0;
        DOWN.yOffset = 1;
        LEFT.xOffset = -1;
        LEFT.yOffset = 0;
        RIGHT.xOffset = 1;
        RIGHT.yOffset = 0;
        NONE.xOffset = 0;
        NONE.yOffset = 0;
    }

    public int getXOffset() {
        return xOffset;
    }

    public int getYOffset() {
        return yOffset;
    }

}