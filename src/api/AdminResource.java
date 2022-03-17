package api;
import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;
import java.util.Collection;
import java.util.List;

/**
 * This contains APIs for admin resources
 * @author Ratan Dheeraj Kadirikota
 */
public class AdminResource {
    public static Customer getCustomer(String email){
        return CustomerService.getCustomer(email);
    }

    public static void addRoom(List<IRoom> rooms){
        for (IRoom room : rooms) {
            ReservationService.addRoom(room);
        }
    }

    public static Collection<IRoom> getAllRooms(){
        return ReservationService.getAllRooms();
    }

    public static Collection<Customer> getAllCustomer(){
        return CustomerService.getAllCustomers();
    }

    public static void displayAllReservations(){
        ReservationService.printAllReservations();
    }
}
