package Controller;

import Entity.Booking;
import Entity.Customer;
import Entity.Room;
import Service.BookingService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

        System.out.println("Enter check-in date (yyyy-MM-dd HH:mm): ");
        String checkInDateStr = sc.nextLine();
        LocalDateTime checkIn = parseDateTime(checkInDateStr);

        System.out.println("Enter check-out date (yyyy-MM-dd HH:mm): ");
        String checkOutDateStr = sc.nextLine();
        LocalDateTime checkOut = parseDateTime(checkOutDateStr);

        if (checkIn == null || checkOut == null) {
            System.out.println("Invalid date format. Please use the format yyyy-MM-dd HH:mm.");
            return;
        }

        // Kiểm tra nếu ngày check-out trước check-in
        if (checkOut.isBefore(checkIn)) {
            System.out.println("Check-out date cannot be before check-in date.");
            return;
        }

        System.out.println("Enter customer name: ");
        String cus_name = sc.nextLine();
        System.out.println("Enter customer phone: ");
        String cus_phone = sc.nextLine();

        Customer customer = findOrCreateCustomer(cus_name, cus_phone);

        // Thực hiện đặt phòng
        Optional<Booking> booking = bookingService.bookRoom(customer, room_id, checkIn, checkOut);

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

        // Lấy thông tin phòng và kiểm tra phòng nào còn trống
        for (Room room : bookingService.getRooms()) {
            boolean isAvailable = true;

            // Kiểm tra các booking đã tồn tại của phòng này
            for (Booking booking : bookingService.getBookings()) {
                // Nếu phòng đang được đặt và trùng với khoảng thời gian hiện tại
                if (booking.getRoom().getId().equals(room.getId()) &&
                        (now.isBefore(booking.getCheck_out_datetime()) && now.isAfter(booking.getCheck_in_datetime()))) {
                    isAvailable = false; // Phòng không trống
                    break;
                }
            }

            // Nếu phòng còn trống, in ra thông tin phòng
            if (isAvailable) {
                System.out.println("ID: " + room.getId() +
                        ", Type: " + room.getRoomType() +
                        ", Price per Hour: " + '$' + room.getPrice_per_hour());
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

    private LocalDateTime parseDateTime(String dateTimeStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (Exception e) {
            return null;  // Trả về null nếu định dạng không hợp lệ
        }
    }
}
