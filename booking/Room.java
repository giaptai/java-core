package booking;

public class Room {
    protected int roomId;
    protected boolean available;
    protected String bookedBy;

    public Room(int roomId) {
        this.roomId = roomId;
    }
}
