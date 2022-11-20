package StepProject.InterDAO;

import StepProject.entities.Flight;

import java.util.List;

public interface FlightDao {
    List<Flight> getAllFlight();
    List<Flight> getFlightById(String id);
    void deleteFlight(String id);
    void saveFlight(Flight f);
}
