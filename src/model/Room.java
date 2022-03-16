package model;

import java.util.Objects;

/**
 * This is the model class for Room, it provides unique identification of the room
 * @author Ratan Dheeraj Kadirikota
 *
 */
public class Room implements IRoom{
     String roomNumber;
     Double price;
     RoomType enumeration;
    public Room(String roomNumber, Double price, RoomType enumeration){
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
    }

    @Override
    public boolean isFree() {
        return (price.equals(0.0)) && (price != null);
    }
    @Override
    public RoomType getRoomType() {
        return enumeration;
    }
    @Override
    public String getRoomNumber() {
        return roomNumber;
    }
    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public String toString(){
        return "Room Details:\n"+"Room number: "+roomNumber+"\nPrice: "+price+"\nRoom type: "+enumeration+" Bed\n";
    }

    @Override
    public boolean equals(Object object) {
        if(object == this) {
            return true;
        }

        if(!(object instanceof Room)) {
            return false;
        }

        Room room = (Room) object;
        return Objects.equals(this.roomNumber, room.roomNumber);
    }

}
