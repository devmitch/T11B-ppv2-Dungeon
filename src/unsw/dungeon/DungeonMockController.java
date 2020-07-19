package unsw.dungeon;

public class DungeonMockController {
    public Dungeon dungeon;
    private Player player;

    public DungeonMockController(Dungeon dungeon) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
    }

    public void movePlayer(Direction D) {
        player.move(D);
    }
}