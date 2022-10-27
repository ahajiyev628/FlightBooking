package StepProject.Controller;

import StepProject.DAO.CollectionBookingDao;
import StepProject.DAO.CollectionFlightDao;
import StepProject.DAO.Flight;
import StepProject.Services.BookingService;
import StepProject.Services.FlightService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;

public class TestUnit {
    private Assertions Assert;
    public static String flightPath = TestUnit.class.getClassLoader().getResource("test3.txt").getFile();
    public static String bookingPath = TestUnit.class.getClassLoader().getResource("test4.txt").getFile();

    File flightFile = new File(flightPath);
    File bookingFile = new File(bookingPath);

    FlightService fs =  new FlightService(new CollectionFlightDao(flightFile));
    BookingService bs =  new BookingService(new CollectionBookingDao(bookingFile));

    FlightController fc = new FlightController(fs);
    BookingController bc = new BookingController(bs);


    @Test
    public void firstTest() throws ParseException {
        // fc.fs.generate();

        Iterator<Flight> iter = fc.fs.cfd.getAllFlight().iterator();
        while(iter.hasNext()) {
            System.out.println(iter.next().toString());
        }

        int beforeBookingSize = fc.fs.cfd.getAllFlight().size();
        fc.fs.cfd.deleteFlight(2);
        int afterBookingSize = fc.fs.cfd.getAllFlight().size();

        org.junit.Assert.assertNotEquals(beforeBookingSize, afterBookingSize);
    }
}
