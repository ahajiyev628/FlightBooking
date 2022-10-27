package StepProject.DAO;

import java.util.List;

public interface FlightDao {
    List<Flight> getAllFlight();
    List<Flight> getFlightById(int id);
    void deleteFlight(int id);
    void saveFlight(Flight f);
}
