package unsw.dungeon;

public class Portal extends Entity {

    private Portal correspondingPortal;
    private int id;

    public Portal(Dungeon dungeon, int x, int y, int id) {
        super(dungeon, x, y, true, true, false);
        this.id = id;
        correspondingPortal = dungeon.getPortalWithId(id);
        if (correspondingPortal != null && correspondingPortal != this)
            correspondingPortal.setCorrespondingPortal(this);
    }
    
    @Override
    public void interactWith(Entity e, Direction d) {
        if (e instanceof Player) {
            Player p = (Player) e;

            // get the portal that corresponds to this portal
            if (correspondingPortal != null) {
                p.moveToEntity(correspondingPortal);
            }
        }
    }

    public int getId() {
        return id;
    }

    /**
     * Sets the corresponding portal (portals have same id).
     * 
     * @param portal the corresponding portal.
     */
    public void setCorrespondingPortal(Portal portal) {
        correspondingPortal = portal;
    }

}