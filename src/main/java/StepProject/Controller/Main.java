package StepProject.Controller;

import StepProject.DAO.BookingApp;
import StepProject.DAO.CollectionBookingDao;
import StepProject.DAO.CollectionFlightDao;
import StepProject.DAO.Flight;
import StepProject.Services.BookingService;
import StepProject.Services.FlightService;

import java.io.*;
import java.text.ParseException;
import java.util.*;

public class Main {
    public static String flightPath = Main.class.getClassLoader().getResource("test3.txt").getFile();
    public static String bookingPath = Main.class.getClassLoader().getResource("test4.txt").getFile();

    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);

        File flightFile = new File(flightPath);
        File bookingFile = new File(bookingPath);

        FlightService fs =  new FlightService(new CollectionFlightDao(flightFile));
        BookingService bs =  new BookingService(new CollectionBookingDao(bookingFile));

        FlightController fc = new FlightController(fs);
        BookingController bc = new BookingController(bs);

        fc.fs.generate();

        Iterator<Flight> iter = fc.fs.cfd.getAllFlight().iterator();
        while(iter.hasNext()) {
            System.out.println(iter.next().toString());
        }

        /* Search flight by ID */
        System.out.println("Search flight by flight ID.");
        System.out.print("Please enter flight ID: ");
        int flightID=sc.nextInt();
        System.out.println(fc.fs.cfd.getFlightById(flightID));

        System.out.println();
        System.out.println();

        // Ticket{flightID=5, origin='Kiev', destination='Baku', flightDate='2023/03/02', flightTime='02:37', seatAvailable=5}

        System.out.println("SEARCH FLIGHT");
        System.out.print("Please enter destination: ");
        String destination = sc.next();
        System.out.print("Please enter date: ");
        String date = sc.next();
        System.out.print("Please enter a number of passengers: ");
        int remainingSeat = sc.nextInt();

        Iterator<Flight> it = fc.fs.searchFlight(destination,date,remainingSeat).iterator();

        while(it.hasNext()) {
            System.out.println(it.next().toString());
        }
//
//        ///////////////////////////////////////////////////////////////////////////////////////////////

        System.out.println();
        System.out.println();

        boolean isBook = true;

        while(isBook) {
            System.out.println("BOOKING FLIGHT");
            System.out.print("Enter a flight id to book: ");
            int bookFLight = sc.nextInt();
            List<Flight> newFlight = fc.fs.cfd.getFlightById(bookFLight);
            Iterator<Flight> itt = newFlight.iterator();
            System.out.println("Enter your name: ");
            String name = sc.next();
            System.out.println("Enter your surname: ");
            String surname = sc.next();

            while (itt.hasNext()) {
                Flight ff = itt.next();

                int bookID = ff.getFlightID();
                String bookOrigin = ff.getOrigin();
                String bookDestination = ff.getDestination();
                String bookDate = ff.getFlightDate();
                String bookTime = ff.getFlightTime();
                int seats = ff.getSeatAvailable();

                BookingApp ba = new BookingApp();
                ba.setPassengerName(name);
                ba.setPassengerSurname(surname);
                ba.setBookingID(bookID);
                ba.setBookingOrigin(bookOrigin);
                ba.setBookingDestination(bookDestination);
                ba.setBookingDate(bookDate);
                ba.setBookingTime(bookTime);

                bc.bs.cbd.saveBooking(ba);

                Iterator<Flight> iter3 = fc.fs.cfd.getFlightById(bookFLight).iterator();
                while (iter3.hasNext()) {
                    Flight fff = iter3.next();
                    fff.setSeatAvailable(seats - 1);
                    fc.fs.cfd.deleteFlight(bookFLight);
                    fc.fs.cfd.saveFlight(fff);
                }
            }
            Iterator<Flight> iter7 = fc.fs.cfd.getAllFlight().iterator();
            while(iter7.hasNext()) {
                System.out.println(iter7.next().toString());
            }

            System.out.println();
            System.out.println("Type anything to continue, or type 'exit' to exit");
            String endBook = sc.next();
            if (endBook.toLowerCase().equals("exit")) {
                isBook = false;
            } else {
                isBook = true;
            }
        }


        Iterator<BookingApp> iter6 = bc.bs.cbd.getAllBooking().iterator();
        while(iter6.hasNext()) {
            System.out.println(iter6.next().toString());
        }

        // booking cancellation
        System.out.println();
        System.out.println();
        System.out.println("BOOKING CANCELLATION");
        System.out.print("Enter a flight id to cancel booking: ");
        int cancelID = sc.nextInt();
        bc.bs.cbd.cancelBooking(cancelID);
        Iterator<Flight> iter5 = fc.fs.cfd.getFlightById(cancelID).iterator();
        while(iter5.hasNext()) {
            Flight ffff = iter5.next();
            ffff.setSeatAvailable(ffff.getSeatAvailable()+1);
            fc.fs.cfd.deleteFlight(cancelID);
            fc.fs.cfd.saveFlight(ffff);
        }

        System.out.println();
        Iterator<BookingApp> iter2 = bc.bs.cbd.getAllBooking().iterator();
        while(iter2.hasNext()) {
            System.out.println(iter2.next().toString());
        }

        System.out.println();
        Iterator<Flight> iter4 = fc.fs.cfd.getAllFlight().iterator();
        while(iter4.hasNext()) {
            System.out.println(iter4.next().toString());
        }

        System.out.println();
        System.out.println();
        System.out.println("My Bookings");
        Iterator<BookingApp> iter8 = bc.bs.myBookings("Allahverdi", "Hajiyev").iterator();
        while(iter8.hasNext()) {
            System.out.println(iter8.next().toString());
        }
    }
}