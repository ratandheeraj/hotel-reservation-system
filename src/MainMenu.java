import api.HotelResource;
import model.IRoom;
import model.Reservation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

/**
 * This is the main menu which has options to reserve a room, view reservations and open admin panel
 * @author Ratan Dheeraj Kadirikota
 */
public class MainMenu {
    private static final String dateFormat = "mm/dd/yyyy";
    public static void mainMenu() {
        Scanner sc = new Scanner(System.in);
        String choice;
        try{
            do{
                printMainMenu();
                choice = sc.nextLine();
                if(choice.length() == 1){
                    switch (Integer.parseInt(choice)) {
                        case 1 -> findAndReserveARoom();
                        case 2 -> seeCustomerReservations();
                        case 3 -> createAccount();
                        case 4 -> AdminMenu.adminMenu();
                        case 5 -> System.out.println("Exiting application");
                        default -> System.out.println("Please choose only from the given menu");
                    }
                }
                else{
                    System.out.println("Invalid input");
                }
            }while(Integer.parseInt(choice) != 5 );
        }catch (Exception ex){
            System.out.println("Invalid input received.");
            mainMenu();
        }

    }

    public static void printMainMenu()
    {
        System.out.println("***Welcome to Hotel Reservation Application***");
        System.out.println("-----------------------------------");
        System.out.println("1. Find and reserve a room\n2. See my reservations\n3. Create an account\n4. Admin\n5. Exit");
        System.out.println("-----------------------------------");
        System.out.println("Select an option from the menu:");
    }

    private static void findAndReserveARoom() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Check-In Date: \nFormat-mm/dd/yyyy (Ex: 01/01/2022)");
        Date checkInDate = getInputDate(sc);
        System.out.println("Enter Check-Out Date: \nFormat-mm/dd/yyyy (Ex:02/21/2020)");
        Date checkOutDate = getInputDate(sc);

        assert checkInDate != null;
        if(checkInDate.after(checkOutDate)){
            System.out.println("Check-In Date should be before Check-Out Date.");
            return;
        }

        Collection<IRoom> availableRooms = HotelResource.findARoom(checkInDate, checkOutDate);

        if (availableRooms.size() == 0) {
            Collection<IRoom> alternativeRooms = HotelResource.findAlternateRooms(checkInDate, checkOutDate);

            if (alternativeRooms.size() == 0) {
                System.out.println("No rooms are found.");
            } else {
                Date alternateCheckInDate = HotelResource.addDefaultPlusDays(checkInDate);
                Date alternateCheckOutDate = HotelResource.addDefaultPlusDays(checkOutDate);
                System.out.println("We've only found rooms on alternative dates:");
                System.out.println("Check-In Date:" + alternateCheckInDate);
                System.out.println("Check-Out Date:" + alternateCheckOutDate);
                printRooms(alternativeRooms);
                reserveRoom(alternateCheckInDate, alternateCheckOutDate, alternativeRooms);
            }
        } else {
            printRooms(availableRooms);
            reserveRoom(checkInDate, checkOutDate, availableRooms);
        }
    }

    private static void seeCustomerReservations() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your Email: (Format: name@domain.com)");
        String email = sc.nextLine();
        Collection<Reservation> reservations = HotelResource.getCustomersReservations(email);
        if (reservations.isEmpty()) {
            System.out.println("No reservation found.");
        } else {
            for(Reservation reservation: reservations){
                System.out.println(reservation);
            }
        }
    }

    private static Date getInputDate(Scanner sc) {
        try {
            return new SimpleDateFormat(dateFormat).parse(sc.nextLine());
        } catch (ParseException ex) {
            System.out.println("Error: Invalid date format. Check and try again.");
            findAndReserveARoom();
        }
        return null;
    }

    private static void reserveRoom(Date checkInDate, Date checkOutDate, Collection<IRoom> rooms) {
        System.out.println("Would you like to book? (y/n)");
        Scanner sc = new Scanner(System.in);
        String bookRoom = sc.nextLine();
        if ("y".equals(bookRoom) || "Y".equals(bookRoom)) {
            System.out.println("Do you have an account with us? (y/n)");
            String haveAccount = sc.nextLine();
            if ("y".equals(haveAccount) || "Y".equals(haveAccount)) {
                System.out.println("Enter Email: (format: username@domain.com)");
                String email = sc.nextLine();
                if (HotelResource.getCustomer(email) == null) {
                    System.out.println("Customer does not exist with given email.\nYou have to create a new account.");
                }
                else {
                    System.out.println("What room number would you like to reserve?");
                    String roomNumber = sc.nextLine();
                    if (rooms.stream().anyMatch(room -> room.getRoomNumber().equals(roomNumber))) {
                        IRoom room = HotelResource.getRoom(roomNumber);
                        Reservation reservation = HotelResource.bookARoom(email, room, checkInDate, checkOutDate);
                        System.out.println("Room is Reserved successfully!");
                        System.out.println(reservation);
                    }
                    else {
                        System.out.println("Error: Room number not available.\nPlease start reservation again.");
                    }
                }
            } else {
                System.out.println("Create an account first.");
            }
        } else if ("n".equals(bookRoom) || "N".equals(bookRoom)){
            return;
        } else {
            reserveRoom(checkInDate, checkOutDate, rooms);
        }
    }

    private static void printRooms(Collection<IRoom> rooms) {
        if (rooms.size() == 0) {
            System.out.println("No rooms found.");
        } else {
            for(IRoom room:rooms){
                System.out.println(room);
            }
        }
    }

    public static void createAccount(){
        String email,firstName,lastName;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter email: (format: username@domain.com)");
        email = sc.nextLine();
        System.out.println("Enter First name:");
        firstName = sc.nextLine();
        System.out.println("Enter Last name:");
        lastName = sc.nextLine();
        try{
            HotelResource.createACustomer(email,firstName,lastName);
            System.out.println("Account created successfully.");
        }
        catch(Exception ex){
            System.out.println(ex+"\nPlease enter proper email id.");
        }
    }
}
