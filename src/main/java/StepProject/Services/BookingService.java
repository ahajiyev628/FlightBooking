package StepProject.Services;

import StepProject.entities.Booking;
import StepProject.DAO.CollectionBookingDao;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class BookingService {
    private File bookingFile;

    public File getBookingFile() {
        return bookingFile;
    }
    public void setBookingFile(File bookingFile) {
        this.bookingFile = bookingFile;
    }
    public CollectionBookingDao cbd = new CollectionBookingDao(getBookingFile());

    public BookingService(CollectionBookingDao cbd) {
        this.cbd = cbd;
    }

    public List<Booking> myBookings(String name, String surname) {
        return cbd.getAllBooking()
                .stream()
                .filter(s -> ((s.getPassengerName().toLowerCase()).equals(name.toLowerCase())
                        && s.getPassengerSurname().equals(surname)))
                .collect(Collectors.toList());
    }
}
