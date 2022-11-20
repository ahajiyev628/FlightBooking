package StepProject.Main;

import StepProject.Controller.BookingController;
import StepProject.Controller.FlightController;
import StepProject.entities.Booking;
import StepProject.DAO.CollectionBookingDao;
import StepProject.DAO.CollectionFlightDao;
import StepProject.entities.Flight;
import StepProject.Services.BookingService;
import StepProject.Services.FlightService;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class BookingMenu {
    public static String flightPath = Main.class.getClassLoader().getResource("flights.txt").getFile();
    public static String bookingPath = Main.class.getClassLoader().getResource("bookings.txt").getFile();

    public static void menuItems() {
        System.out.println();
        System.out.println("*****************************************************************************");
        System.out.println("Welcome to the Flight Booking System. Please, follow the instructions below: ");
        System.out.println("Enter '1' to see all the flights currenctly available");
        System.out.println("Enter '2' if you need a specific flight");
        System.out.println("Enter '3' to filter flights based on main flight details");
        System.out.println("Enter '4' to book a flight");
        System.out.println("Enter '5' to cancel a flight");
        System.out.println("Enter '6' to view all your bookings");
        System.out.println("Enter '0' to exit from the system");
        System.out.println("*****************************************************************************");
        System.out.println();
        System.out.println("Please enter one of the 7 numbers above: ");
    }

    public void menu() throws IOException, ClassNotFoundException, ParseException {
        Scanner sc = new Scanner(System.in);
        File flightFile = new File(flightPath);
        File bookingFile = new File(bookingPath);

        FlightService fs =  new FlightService(new CollectionFlightDao(flightFile));
        BookingService bs =  new BookingService(new CollectionBookingDao(bookingFile));

        FlightController fc = new FlightController(fs);
        BookingController bc = new BookingController(bs);

        fs.generate();

        menuItems();
        int userInput = sc.nextInt();

        boolean isBook = true;

        while (isBook) {
            switch (userInput) {
                case 1:
                Iterator<Flight> iter4 = fs.cfd.getAllFlight().iterator();
                while (iter4.hasNext()) {
                    System.out.println(iter4.next().toString());
                }
                    menuItems();
                    userInput = sc.nextInt();
                    break;

                case 2:
                    System.out.println("Search flight by flight ID.");
                    System.out.print("Please enter flight ID: ");
                    String flightID = sc.next();
                    System.out.println(fs.cfd.getFlightById(flightID));
                    menuItems();
                    userInput = sc.nextInt();
                    break;

                case 3:
                    System.out.println("SEARCH FLIGHT");
                    System.out.print("Please enter destination: ");
                    String destination = sc.next();
                    System.out.print("Please enter date: ");
                    String date = sc.next();
                    System.out.print("Please enter a number of passengers: ");
                    int remainingSeat = sc.nextInt();

                    Iterator<Flight> it = fs.searchFlight(destination, date, remainingSeat).iterator();

                    while (it.hasNext()) {
                        System.out.println(it.next().toString());
                    }
                    menuItems();
                    userInput = sc.nextInt();
                    break;

                case 4:
                    System.out.print("Enter a flight id to book: ");
                    String bookFLight = sc.next();
                    List<Flight> newFlight = fs.cfd.getFlightById(bookFLight);
                    Iterator<Flight> itt = newFlight.iterator();
                    System.out.println("Enter your name: ");
                    String name = sc.next();
                    System.out.println("Enter your surname: ");
                    String surname = sc.next();


                    while (itt.hasNext()) {
                        Flight ff = itt.next();

                        String bookID = ff.getFlightID();
                        String bookOrigin = ff.getOrigin();
                        String bookDestination = ff.getDestination();
                        String bookDate = ff.getFlightDate();
                        String bookTime = ff.getFlightTime();
                        int seats = ff.getSeatAvailable();

                        Booking ba = new Booking();
                        ba.setPassengerName(name);
                        ba.setPassengerSurname(surname);
                        ba.setBookingID(bookID);
                        ba.setBookingOrigin(bookOrigin);
                        ba.setBookingDestination(bookDestination);
                        ba.setBookingDate(bookDate);
                        ba.setBookingTime(bookTime);

                        bs.cbd.saveBooking(ba);

                        Iterator<Flight> iter3 = fs.cfd.getFlightById(bookFLight).iterator();
                        while (iter3.hasNext()) {
                            Flight fff = iter3.next();
                            fff.setSeatAvailable(seats - 1);
                            fs.cfd.deleteFlight(bookFLight);
                            fs.cfd.saveFlight(fff);
                        }
                    }
                    menuItems();
                    userInput = sc.nextInt();
                    break;

                case 5:
                    System.out.print("Enter a flight id to cancel booking: ");
                    String cancelID = sc.next();
                    bs.cbd.cancelBooking(cancelID);
                    Iterator<Flight> iter5 = fs.cfd.getFlightById(cancelID).iterator();
                    while (iter5.hasNext()) {
                        Flight ffff = iter5.next();
                        ffff.setSeatAvailable(ffff.getSeatAvailable() + 1);
                        fs.cfd.deleteFlight(cancelID);
                        fs.cfd.saveFlight(ffff);
                    }

                    System.out.println();
                    Iterator<Booking> iter2 = bs.cbd.getAllBooking().iterator();
                    while (iter2.hasNext()) {
                        System.out.println(iter2.next().toString());
                    }
                    menuItems();
                    userInput = sc.nextInt();
                    break;

                case 6:
                    System.out.println("My Bookings");
                    Iterator<Booking> iter8 = bs.cbd.getAllBooking().iterator();
                    while(iter8.hasNext()) {
                        System.out.println(iter8.next().toString());
                    }
                    menuItems();
                    userInput = sc.nextInt();
                    break;

                case 0:
                    isBook = false;
                    break;

                default:
                    System.out.println("Please enter exactly one of the 7 nnumbers specified");
                    menuItems();
                    userInput = sc.nextInt();
            }
        }
    }
}