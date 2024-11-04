package Controller;

import Service.BookingService;

public class BookingController {
    private BookingService bookingService;
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public void bookRoom() {
        bookingService.bookRoom();
    }

    public void findBooking() {
        bookingService.findBooking();
    }

    public void revenueByRoomType() {
        bookingService.revenueByRoomType();
    }

    public void displayHighestRevenue() {
        bookingService.displayHighestRevenue();
    }
}
