package model;

import java.util.Date;

/**
 * Class to store reservations
 * @author Ratan Dheeraj Kadirikota
 */
public class Reservation {
    Customer customer;
    IRoom room;
    Date checkInDate;
    Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public IRoom getRoom() {
        return room;
    }
    public Customer getCustomer() {
        return customer;
    }
    public Date getCheckInDate() {
        return checkInDate;
    }
    public Date getCheckOutDate() {
        return checkOutDate;
    }

    @Override
    public String toString(){
        return "Customer:\n"+customer+"\nRoom:"+room+"\nCheck in date: "+checkInDate+"\nCheck out date: "+checkOutDate;
    }

}
