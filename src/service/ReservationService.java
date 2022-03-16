package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This class handles reservations service
 * @author Ratan Dheeraj Kadirikota
 */
public class ReservationService {
    static Map<String, IRoom> rooms = new HashMap<>();
    static ArrayList<Reservation> reservations = new ArrayList<>();
    private static final int defaultPlusDays = 7;

    public static void addRoom(IRoom room){
        rooms.put(room.getRoomNumber(),room);
    }
    public static IRoom getARoom(String roomId){
        return rooms.get(roomId);
    }
    public static Collection<IRoom> getAllRooms() {
        return rooms.values();
    }
    public static Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        Reservation reservation = new Reservation(customer,room,checkInDate,checkOutDate);
        reservations.add(reservation);
        return reservation;
    }
    public static Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate){
        return findAvailableRooms(checkInDate,checkOutDate);
    }
    public static Collection<Reservation> getCustomersReservation(Customer customer){
        ArrayList<Reservation> customerReservations = new ArrayList<>();
        for(Reservation reservation:reservations){
            if(reservation.getCustomer() == customer){
                customerReservations.add(reservation);
            }
        }
        return customerReservations;
    }
    public static void printAllReservations(){
        for(Reservation reservation:reservations){
            System.out.println(reservation);
        }
    }
    public static Collection<IRoom> findAlternateRooms(Date checkInDate,Date checkOutDate) {
        return findAvailableRooms(addDefaultPlusDays(checkInDate), addDefaultPlusDays(checkOutDate));
    }

    private static Collection<IRoom> findAvailableRooms(Date checkInDate, Date checkOutDate) {
        Collection<Reservation> allReservations = new ArrayList<>(reservations);
        Collection<IRoom> notAvailableRooms = new ArrayList<>();

        for (Reservation reservation : allReservations) {
            if (doesReservationOverlap(reservation, checkInDate, checkOutDate)) {
                notAvailableRooms.add(reservation.getRoom());
            }
        }

        return rooms.values().stream().filter(room -> notAvailableRooms.stream()
                        .noneMatch(notAvailableRoom -> notAvailableRoom.equals(room)))
                .collect(Collectors.toList());
    }
    public static Date addDefaultPlusDays(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, defaultPlusDays);
        return calendar.getTime();
    }

    private static boolean doesReservationOverlap(Reservation reservation, Date checkInDate, Date checkOutDate){
        if(checkInDate.before(reservation.getCheckOutDate())
                && checkOutDate.after(reservation.getCheckInDate())){
            return true;
        }
        else{
            return false;
        }
    }


}
