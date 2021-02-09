public class Room {
    private String description;
    private Room northExit;
    private Room southExit;
    private Room eastExit;
    private Room westExit;

    public Room(String description) {
        this.description = description;
    }

    public void setExits(Room north, Room east, Room south, Room west) {
        if (north != null)
            northExit = north;
        if (east != null)
            eastExit = east;
        if (south != null)
            southExit = south;
        if (west != null)
            westExit = west;
    }

    public String getDescription() {
        return description;
    }

    public Room getNorthExit(){
        return northExit;
    }

    public Room getSouthExit(){
        return southExit;
    }

    public Room getEastExit(){
        return eastExit;
    }

    public Room getWestExit(){
        return westExit;
    }

    public boolean isNull(){
        return false;
    }
}

