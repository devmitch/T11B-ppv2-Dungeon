package unsw.dungeon;

public class Wall extends Entity {

    public Wall(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y, true, true, false);
    }

    @Override
    public void interactWith(Entity e, Direction d) {
        if (e instanceof Player) {
            Player p = (Player) e;
            if (p.isInvisible()) {
                int targetX = getX() + d.getXOffset();
                int targetY = getY() + d.getYOffset();
                if (!dungeon.isTileObstructed(targetX, targetY)) {
                    dungeon.moveEntity(p, targetX, targetY);
                    p.stun(1);
                }
            }
        }
    }

}
