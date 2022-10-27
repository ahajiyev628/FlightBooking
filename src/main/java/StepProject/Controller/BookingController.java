package StepProject.Controller;

import StepProject.Services.BookingService;

public class BookingController {
    BookingService bs;

    public BookingController(BookingService bs) {
        this.bs = bs;
    }
}
