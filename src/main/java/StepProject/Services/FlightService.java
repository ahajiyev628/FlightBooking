package StepProject.Services;

import StepProject.DAO.CollectionFlightDao;
import StepProject.entities.Destination;
import StepProject.entities.Flight;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class FlightService {
    private File f;

    public File getF() {
        return f;
    }
    public CollectionFlightDao cfd;


    public FlightService(CollectionFlightDao cfd) {
        this.cfd = cfd;
    }

    public List<Flight> searchFlight(String destination, String date, int remainingSeat) throws IOException, ClassNotFoundException {
        return cfd.getAllFlight()
                .stream()
                .filter(s -> ((s.getDestination().toLowerCase()).equals(destination.toLowerCase())
                        && s.getFlightDate().equals(date)
                        && s.getSeatAvailable()>=remainingSeat))
                .collect(Collectors.toList());
    }

    Calendar c = Calendar.getInstance(Locale.ENGLISH);

    public String randomDateGenerator() {
        Date d = c.getTime();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
        return sdf1.format(d) + (int) (Math.random() * 10);
    }

    public String randomDateTimeGenerator() {
        Date d = c.getTime();
        SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm");
        return sdf2.format(d) + (int) (Math.random() * 100);
    }

    public String randomIdGenerator() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 5;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    public void generate() throws ParseException {
        for (int i = 0; i <10; i++) {
            cfd.saveFlight(new Flight(randomIdGenerator(),"Kiev", Destination.randomDestination().getCode(), randomDateGenerator(), randomDateTimeGenerator(), (int)(Math.random()*10)));
        }
    }
}
