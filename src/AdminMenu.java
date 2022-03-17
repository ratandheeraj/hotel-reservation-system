import api.AdminResource;
import model.*;
import java.util.*;

/**
 * This is the admin menu which has options to view reservations, customers, rooms and also can create rooms
 * @author Ratan Dheeraj Kadirikota
 */

public class AdminMenu {
    public static void adminMenu(){
        Scanner sc = new Scanner(System.in);
        String choice;
        try{
            do{
                printAdminMenu();
                choice = sc.nextLine();
                if(choice.length() == 1){
                    switch (Integer.parseInt(choice)) {
                        case 1 -> seeAllCustomers();
                        case 2 -> seeAllRooms();
                        case 3 -> seeAllReservations();
                        case 4 -> addRooms();
                        case 5 -> MainMenu.mainMenu();
                        default -> System.out.println("Please choose only from the given menu");
                    }
                }
                else{
                    System.out.println("Invalid input");
                }
            }while(Integer.parseInt(choice) != 5 );
        }catch (Exception ex){
            System.out.println("Invalid input received.");
        }

    }

    public static void printAdminMenu()
    {
        System.out.println("***Welcome to Admin Menu***");
        System.out.println("-----------------------------------");
        System.out.println("1. See all Customers\n2. See all Rooms\n3. See all Reservations\n4. Add a Room\n5. Back to Main Menu");
        System.out.println("-----------------------------------");
        System.out.println("Select an option from the menu");
    }

    public static IRoom addARoom() {
        String roomNumber, roomType;
        double price;
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Room Number:");
        roomNumber = sc.nextLine();
        System.out.println("Enter Price per night:");
        price = Double.parseDouble(sc.nextLine());
        System.out.println("Enter Room Type: 1 for Single bed, 2 for Double bed:");
        roomType = sc.nextLine();
        if (roomType.equals("1")) {
            return new Room(roomNumber, price, RoomType.SINGLE);
        } else if (roomType.equals("2")) {
            return new Room(roomNumber, price, RoomType.DOUBLE);
        }else{
            System.out.println("Invalid room type");
            return null;
        }
    }

    public static void addRooms(){
        List<IRoom> rooms = new ArrayList<>();
        String choice;
        Scanner sc = new Scanner(System.in);
        while(true){
            rooms.add(addARoom());
            System.out.println("Would you like to add another room? y/n");
            choice = sc.nextLine();
            if (choice.equals("N") || choice.equals("n")) {
                break;
            } else if (choice.equals("Y") || choice.equals("y")) {
                continue;
            } else{
                while(true){
                    System.out.println("Invalid input, enter y for yes or n for no");
                    choice = sc.nextLine();
                    if (choice.equals("N") || choice.equals("n")) {
                        AdminResource.addRoom(rooms);
                        return;
                    } else if (choice.equals("Y") || choice.equals("y")) {
                        break;

                    }
                }
            }
        }
        AdminResource.addRoom(rooms);
    }

    public static void seeAllRooms(){
        Collection<IRoom> rooms = AdminResource.getAllRooms();
        for(IRoom room:rooms){
            System.out.println(room.toString());
        }
    }

    public static void seeAllCustomers(){
        Collection<Customer> customers = AdminResource.getAllCustomer();
        for(Customer customer:customers){
            System.out.println(customer.toString());
        }
    }

    public static void seeAllReservations(){
        AdminResource.displayAllReservations();
    }

}

