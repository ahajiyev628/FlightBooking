package StepProject.InterDAO;

import StepProject.entities.Booking;

import java.util.List;

public interface BookingDao {
    List<Booking> getAllBooking();
    List<Booking> getBookingById(String id);
    void cancelBooking(String id);
    void saveBooking(Booking b);
}
