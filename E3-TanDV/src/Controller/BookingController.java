package Controller;

import Entity.Booking;
import Entity.Customer;
import Entity.Room;
import Service.BookingService;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class BookingController {
    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public void bookRoom() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Available room service:");
        showAvailableRooms();

        System.out.println("Enter room id to book: ");
        String room_id = sc.nextLine();

        System.out.println("Enter the number of days to book: ");
        int daysToBook = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter customer name: ");
        String cus_name = sc.nextLine();
        System.out.println("Enter customer phone: ");
        String cus_phone = sc.nextLine();

        Customer customer = findOrCreateCustomer(cus_name, cus_phone);

        Optional<Booking> booking = bookingService.bookRoom(customer, room_id, daysToBook);

        if (booking.isPresent()) {
            System.out.println("Booking successfully: " + booking.get());
        } else {
            System.out.println("No rooms available or invalid room id!");
        }
    }

    public void findBooking() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter customer name: ");
        String cus_name = sc.nextLine();
        System.out.println("Enter customer phone: ");
        String cus_phone = sc.nextLine();
        System.out.println("Enter room id: ");
        String room_id = sc.nextLine();

        Optional<Booking> booking = bookingService.findBooking(cus_name, cus_phone, room_id);

        if (booking.isPresent()) {
            System.out.println("Booking found: " + booking.get());
        } else {
            System.out.println("Room not found!");
        }
    }

    public void displayRevenueByRoomType() {
        bookingService.revenueByRoomType().forEach((roomType, revenue) -> {
            System.out.println(roomType + ": $" + revenue);
        });
    }

    public void displayHighestRevenue() {
        Optional<Map.Entry<Room, Double>> highestRevenue = bookingService.displayHighestRevenue();

        if (highestRevenue.isPresent()) {
            System.out.println("Highest revenue room: " + highestRevenue.get().getKey() + " with revenue $" + highestRevenue.get().getValue());
        } else {
            System.out.println("No bookings found for the year 2023.");
        }
    }

    private void showAvailableRooms() {
        LocalDateTime now = LocalDateTime.now();

        Map<String, Integer> roomsInUse = new HashMap<>();

        for (Booking booking : bookingService.getBookings()) {
            if (now.isBefore(booking.getCheck_out_datetime())) {
                String roomId = booking.getRoom().getId();
                roomsInUse.put(roomId, roomsInUse.getOrDefault(roomId, 0) + 1);
            }
        }

        for (Room room : bookingService.getRooms()) {
            int totalRooms = room.getNumber_of_rooms();
            int roomsOccupied = roomsInUse.getOrDefault(room.getId(), 0);

            int availableRooms = totalRooms - roomsOccupied;
            if (availableRooms > 0) {
                System.out.println("Room " + room.getRoomType() + "(ID:" + room.getId() + ")" + " has " + availableRooms + " rooms available.");
            }
        }
    }

    private Customer findOrCreateCustomer(String name, String phone) {
        for (Customer customer : bookingService.getCustomers()) {
            if (customer.getCus_name().equalsIgnoreCase(name) && customer.getCus_phone().equalsIgnoreCase(phone)) {
                return customer;
            }
        }
        int newId = bookingService.getCustomers().size() + 1;
        Customer newCustomer = new Customer(newId, name, phone);
        bookingService.getCustomers().add(newCustomer);
        return newCustomer;
    }
}
