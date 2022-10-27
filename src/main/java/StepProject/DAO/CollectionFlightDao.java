package StepProject.DAO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionFlightDao implements FlightDao {
    private File file;

    public CollectionFlightDao(File file) {
        this.file = file;
    }

    @Override
    public List<Flight> getAllFlight() {
        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis);
             ObjectInputStream ois = new ObjectInputStream(bis);)
        {
            Object flights = ois.readObject();
            List<Flight> allFlights = (ArrayList<Flight>) flights;
            return allFlights;
        } catch (IOException | ClassNotFoundException ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Flight> getFlightById(int id) {
        return getAllFlight().stream().filter(s -> s.getFlightID() == id).collect(Collectors.toList());
    }

    @Override
    public void saveFlight(Flight f) {
        List<Flight> flights = getAllFlight();
        flights.add(f);
        write(flights);
    }

    @Override
    public void deleteFlight(int id) {
        List<Flight> flights = getAllFlight().stream().filter(s -> s.getFlightID() != id).collect(Collectors.toList());
        write(flights);
    }

    private void write(List<Flight> flights) {
        try (FileOutputStream fos = new FileOutputStream(file);
             BufferedOutputStream bos = new BufferedOutputStream(fos);
             ObjectOutputStream oos = new ObjectOutputStream(bos);)
        {
            oos.writeObject(flights);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

