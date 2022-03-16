package model;

/**
 * Interface for room
 * @author Ratan Dheeraj Kadirikota
 */
public interface IRoom {
    public String getRoomNumber();
    public Double getRoomPrice();
    public RoomType getRoomType();
    public boolean isFree();
}
