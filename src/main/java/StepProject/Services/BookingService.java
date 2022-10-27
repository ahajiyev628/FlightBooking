package StepProject.Services;

import StepProject.DAO.BookingApp;
import StepProject.DAO.CollectionBookingDao;
import StepProject.DAO.Flight;

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

    public List<BookingApp> myBookings(String name, String surname) {
        return cbd.getAllBooking()
                .stream()
                .filter(s -> ((s.getPassengerName().toLowerCase()).equals(name.toLowerCase())
                        && s.getPassengerSurname().equals(surname)))
                .collect(Collectors.toList());
    }
}
