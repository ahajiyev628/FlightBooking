package StepProject.DAO;

import java.util.List;

public interface BookingDao {
    List<BookingApp> getAllBooking();
    List<BookingApp> getBookingById(int id);
    void cancelBooking(int id);
    void saveBooking(BookingApp b);
}
