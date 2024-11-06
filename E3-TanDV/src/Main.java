import Controller.BookingController;
import Entity.Booking;
import Entity.Customer;
import Entity.Room;
import Entity.roomType;
import Service.BookingService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Room> rooms = new ArrayList<Room>();
        List<Customer> customers = new ArrayList<>();
        List<Booking> bookings = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        BookingService bookingService = new BookingService(rooms, customers, bookings);
        BookingController bookingController = new BookingController(bookingService);

        int choice = -1;

        rooms.add(new Room("RS001", roomType.Single, 8, 10));
        rooms.add(new Room("RD001", roomType.Double, 12, 5));
        rooms.add(new Room("RQ002", roomType.Queen, 35, 2));
        rooms.add(new Room("RT001", roomType.Triple, 12.5f, 3));
        rooms.add(new Room("RS001", roomType.Quad, 20.5f, 3));

        customers.add(new Customer(1, "Mr.Linus Torvald", "84125325346457"));
        customers.add(new Customer(2, "Mr.Bill", "91124235346467"));
        customers.add(new Customer(3, "Mr.Turning", "911423534646"));

        bookings.add(new Booking(1, rooms.getFirst(), customers.getFirst(), LocalDateTime.of(2023, 3, 15, 9, 30, 15), LocalDateTime.of(2023, 3, 16, 12, 30, 45)));
        bookings.add(new Booking(2, rooms.getFirst(), customers.get(1), LocalDateTime.of(2023, 6, 9, 19, 30, 25), LocalDateTime.of(2023, 6, 10, 11, 25, 15)));
        bookings.add(new Booking(3, rooms.get(1), customers.get(1), LocalDateTime.of(2023, 3, 11, 10, 10, 5), LocalDateTime.of(2023, 3, 13, 11, 5, 10)));
        bookings.add(new Booking(4, rooms.get(3), customers.get(2), LocalDateTime.of(2023, 11, 11, 11, 11, 15), LocalDateTime.of(2023, 11, 13, 11, 15, 15)));
        bookings.add(new Booking(5, rooms.get(3), customers.getFirst(), LocalDateTime.of(2023, 10, 25, 9, 20, 25), LocalDateTime.of(2023, 10, 26, 12, 25, 30)));
        bookings.add(new Booking(6, rooms.get(4), customers.getFirst(), LocalDateTime.of(2023, 8, 18, 18, 25, 35), LocalDateTime.of(2023, 8, 19, 11, 35, 20)));

        do {
            System.out.println("Welcome!");
            System.out.println("1.Booking room| 2.Find Booking| 3.Revenue statistics| 4.Largest revenue room| 5.Exit");
            System.out.println("Please enter your choice:");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    bookingController.bookRoom();
                    break;
                case 2:
                    bookingController.findBooking();
                    break;
                case 3:
                    bookingController.displayRevenueByRoomType();
                    break;
                case 4:
                    bookingController.displayHighestRevenue();
                    break;
            }
        } while (choice != 5);

    }
}