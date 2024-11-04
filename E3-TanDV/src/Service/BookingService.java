package Service;

import Entity.Booking;
import Entity.Customer;
import Entity.Room;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class BookingService {
    private List<Room> rooms;
    private List<Customer> customers;
    private List<Booking> bookings = new ArrayList<>();

    public BookingService(List<Room> rooms, List<Customer> customers, List<Booking> bookings) {
        this.rooms = rooms;
        this.customers = customers;
        this.bookings = bookings;
    }

    public void bookRoom() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter customer name: ");
        String cus_name = sc.nextLine();
        System.out.println("Enter customer phone: ");
        String cus_phone = sc.nextLine();

        Customer customer = findOrCreateCustomer(cus_name, cus_phone);

        System.out.println("Room service:");
        for (Room room : rooms) {
            System.out.println(room);
        }

        System.out.println("Enter room id to book: ");
        String room_id = sc.nextLine();
        Optional<Room> room = rooms.stream()
                .filter(r -> r.getId().equalsIgnoreCase(room_id))
                .findFirst();
        if (room.isPresent()) {
            Room roomToBook = room.get();
            Booking booking = new Booking(bookings.size() + 1, roomToBook, customer, LocalDateTime.now(), LocalDateTime.now().plusDays(3));
            bookings.add(booking);
            System.out.println("Booking successfully: " + booking);
        } else {
            System.out.println("No room found with ID: " + room_id);
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
        Optional<Booking> booking = bookings.stream()
                .filter(b -> b.getCustomer().getCus_name().equalsIgnoreCase(cus_name)
                        && b.getCustomer().getCus_phone().equalsIgnoreCase(cus_phone)
                        && b.getRoom().getId().equalsIgnoreCase(room_id))
                .findFirst();
        if(booking.isPresent()) {
            System.out.println("Booking found: " + booking.get());
        } else {
            System.out.println("Room not found!");
        }
    }

    public void revenueByRoomType() {
        Map<Room, Double> revenueMap = new HashMap<>();

        for (Booking booking : bookings) {
            Room room = findRoomById(booking.getRoom().getId());
            if (room != null) {
                double hoursBooked = Duration.between(booking.getCheck_in_datetime(), booking.getCheck_out_datetime()).toHours();
                double revenue = room.getPrice_per_hour() * hoursBooked;

                revenueMap.put(room, revenue);
            }
        }

        for (Map.Entry<Room, Double> entry : revenueMap.entrySet()) {
            System.out.println(entry.getKey() + ": $" + entry.getValue());
        }
    }

    public void displayHighestRevenue() {
        Map<Room, Double> revenueMap = new HashMap<>();

        for (Booking booking : bookings) {
            if (booking.getCheck_in_datetime().getYear() == 2023) {
                Room room = findRoomById(booking.getRoom().getId());
                if(room != null) {
                    double hoursBooked = Duration.between(booking.getCheck_in_datetime(), booking.getCheck_out_datetime()).toHours();
                    double revenue = room.getPrice_per_hour() * hoursBooked;

                    revenueMap.put(room, revenue);
                }
            }
        }

        Map.Entry<Room, Double> highestRevenue = revenueMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);

        if (highestRevenue != null) {
            System.out.println(highestRevenue.getKey() + ": $" + highestRevenue.getValue());
        } else {
            System.out.println("No bookings found for the year 2023.");
        }
    }

    private Customer findOrCreateCustomer(String name, String phone) {
        for (Customer customer : customers) {
            if(customer.getCus_name().equalsIgnoreCase(name) && customer.getCus_phone().equalsIgnoreCase(phone)) {
                return customer;
            }
        }

        int newId = customers.size() + 1;
        Customer newCustomer = new Customer(newId, name, phone);
        customers.add(newCustomer);
        return newCustomer;
    }

    public Room findRoomById(String id) {
        for (Room room : rooms) {
            if (room.getId().equalsIgnoreCase(id)) {
                return room;
            }
        }
        return null;
    }
}
