package StepProject.DAO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionBookingDao implements BookingDao {
    private File bookingFile;

    public CollectionBookingDao(File bookingFile) {
        this.bookingFile = bookingFile;
    }
    @Override
    public List<BookingApp> getAllBooking() {
        try (FileInputStream fis = new FileInputStream(bookingFile);
             BufferedInputStream bis = new BufferedInputStream(fis);
             ObjectInputStream ois = new ObjectInputStream(bis);)
        {
            Object bookings = ois.readObject();
            List<BookingApp> allBookins = (ArrayList<BookingApp>) bookings;
            return allBookins;
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<>();
        }
    }

    private void writeBooking(List<BookingApp> bookings) {
        try (FileOutputStream fos = new FileOutputStream(bookingFile);
             BufferedOutputStream bos = new BufferedOutputStream(fos);
             ObjectOutputStream oos = new ObjectOutputStream(bos);)
        {
            oos.writeObject(bookings);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<BookingApp> getBookingById(int id) {
        return getAllBooking().stream().filter(s -> s.getBookingID() == id).collect(Collectors.toList());
    }

    @Override
    public void cancelBooking(int id) {
        List<BookingApp> bookings = getAllBooking().stream().filter(s -> s.getBookingID() != id).collect(Collectors.toList());
        writeBooking(bookings);
    }

    @Override
    public void saveBooking(BookingApp b) {
        List<BookingApp> bookings = getAllBooking();
        bookings.add(b);
        writeBooking(bookings);
    }
}

