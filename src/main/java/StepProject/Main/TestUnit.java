package StepProject.Main;

import StepProject.Controller.BookingController;
import StepProject.Controller.FlightController;
import StepProject.DAO.CollectionBookingDao;
import StepProject.DAO.CollectionFlightDao;
import StepProject.entities.Flight;
import StepProject.Services.BookingService;
import StepProject.Services.FlightService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;

public class TestUnit {
    private Assertions Assert;
    public static String flightPath = TestUnit.class.getClassLoader().getResource("flights.txt").getFile();
    public static String bookingPath = TestUnit.class.getClassLoader().getResource("bookings.txt").getFile();

    File flightFile = new File(flightPath);
    File bookingFile = new File(bookingPath);

    FlightService fs =  new FlightService(new CollectionFlightDao(flightFile));
    BookingService bs =  new BookingService(new CollectionBookingDao(bookingFile));

    FlightController fc = new FlightController(fs);
    BookingController bc = new BookingController(bs);

    public TestUnit() throws IOException {
    }

    @Test
    public void firstTest() throws ParseException, IOException, ClassNotFoundException {
        // fc.fs.generate();

        Iterator<Flight> iter = fs.cfd.getAllFlight().iterator();
        while(iter.hasNext()) {
            System.out.println(iter.next().toString());
        }

        int beforeBookingSize = fs.cfd.getAllFlight().size();
        fs.cfd.deleteFlight("UT9NC");
        int afterBookingSize = fs.cfd.getAllFlight().size();

        org.junit.Assert.assertNotEquals(beforeBookingSize, afterBookingSize);
    }
}
