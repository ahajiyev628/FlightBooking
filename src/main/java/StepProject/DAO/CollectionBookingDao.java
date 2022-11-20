package StepProject.DAO;

import StepProject.InterDAO.BookingDao;
import StepProject.entities.Booking;

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
    public List<Booking> getAllBooking() {
        try (FileInputStream fis = new FileInputStream(bookingFile);
             BufferedInputStream bis = new BufferedInputStream(fis);
             ObjectInputStream ois = new ObjectInputStream(bis);)
        {
            Object bookings = ois.readObject();
            List<Booking> allBookins = (ArrayList<Booking>) bookings;
            return allBookins;
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<>();
        }
    }

    private void writeBooking(List<Booking> bookings) {
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
    public List<Booking> getBookingById(String id) {
        return getAllBooking().stream().filter(s -> s.getBookingID().equals(id)).collect(Collectors.toList());
    }

    @Override
    public void cancelBooking(String id) {
        List<Booking> bookings = getAllBooking().stream().filter(s -> s.getBookingID().equals(id)).collect(Collectors.toList());
        writeBooking(bookings);
    }

    @Override
    public void saveBooking(Booking b) {
        List<Booking> bookings = getAllBooking();
        bookings.add(b);
        writeBooking(bookings);
    }
}

