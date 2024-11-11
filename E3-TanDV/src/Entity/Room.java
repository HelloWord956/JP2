package Entity;

public class Room {
    private String id;
    private roomType roomType;
    private float price_per_hour;

    public Room(){;}
    public Room(String id, roomType roomType, float price_per_hour) {
        this.id = id;
        this.roomType = roomType;
        this.price_per_hour = price_per_hour;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public roomType getRoomType() {
        return roomType;
    }

    public void setRoomType(roomType roomType) {
        this.roomType = roomType;
    }

    public float getPrice_per_hour() {
        return price_per_hour;
    }

    public void setPrice_per_hour(float price_per_hour) {
        this.price_per_hour = price_per_hour;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id='" + id + '\'' +
                ", roomType=" + roomType +
                ", price_per_hour=" + price_per_hour +
                '}';
    }
}
