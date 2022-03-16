package model;

/**
 * Class dedicated for free rooms
 * @author Ratan Dheeraj Kadirikota
 */
public class FreeRoom extends Room{
    public FreeRoom(String roomNumber, RoomType enumeration){
        super(roomNumber,0.0,enumeration);

    }

    @Override
    public String toString() {
        return "Free room:"+super.toString();
    }
}
