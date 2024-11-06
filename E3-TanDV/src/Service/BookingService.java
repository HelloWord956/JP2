package Service;

import Entity.Booking;
import Entity.Customer;
import Entity.Room;
import Entity.roomType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class BookingService {
    private List<Room> rooms;
    private List<Customer> customers;
    private List<Booking> bookings;

    public BookingService(List<Room> rooms, List<Customer> customers, List<Booking> bookings) {
        this.rooms = rooms;
        this.customers = customers;
        this.bookings = bookings;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public Optional<Booking> bookRoom(Customer customer, String roomId, int daysToBook) {
        Optional<Room> room = rooms.stream()
                .filter(r -> r.getId().equalsIgnoreCase(roomId))
                .findFirst();

        if (room.isPresent()) {
            Room roomToBook = room.get();

            long bookedRooms = bookings.stream()
                    .filter(booking -> booking.getRoom().equals(roomToBook))
                    .count();

            if (roomToBook.getNumber_of_rooms() - bookedRooms > 0) {
                Booking booking = new Booking(bookings.size() + 1, roomToBook, customer, LocalDateTime.now(), LocalDateTime.now().plusDays(daysToBook));
                bookings.add(booking);
                return Optional.of(booking);
            } else {
                return Optional.empty();
            }
        }

        return Optional.empty();
    }

    public Optional<Booking> findBooking(String customerName, String customerPhone, String roomId) {
        return bookings.stream()
                .filter(b -> b.getCustomer().getCus_name().equalsIgnoreCase(customerName)
                        && b.getCustomer().getCus_phone().equalsIgnoreCase(customerPhone)
                        && b.getRoom().getId().equalsIgnoreCase(roomId))
                .findFirst();
    }

    public Map<roomType, Double> revenueByRoomType() {
        return bookings.stream()
                .collect(Collectors.groupingBy(
                        booking -> booking.getRoom().getRoomType(),
                        Collectors.summingDouble(booking -> {
                            double hoursBooked = Duration.between(booking.getCheck_in_datetime(), booking.getCheck_out_datetime()).toHours();
                            return booking.getRoom().getPrice_per_hour() * hoursBooked;
                        })
                ));
    }

    public Optional<Map.Entry<Room, Double>> displayHighestRevenue() {
        Map<Room, Double> revenueMap = bookings.stream()
                .filter(booking -> booking.getCheck_in_datetime().getYear() == 2023)
                .collect(Collectors.groupingBy(
                        Booking::getRoom,
                        Collectors.summingDouble(booking -> {
                            double hoursBooked = Duration.between(booking.getCheck_in_datetime(), booking.getCheck_out_datetime()).toHours();
                            return booking.getRoom().getPrice_per_hour() * hoursBooked;
                        })
                ));

        return revenueMap.entrySet().stream()
                .max(Map.Entry.comparingByValue());
    }
}
