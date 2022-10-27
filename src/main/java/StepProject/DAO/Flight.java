package StepProject.DAO;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Flight implements Serializable {
    private int flightID;
    private String origin;
    private String destination;
    private String flightDate;
    private String flightTime;
    private int seatAvailable;

    private static final long serialVersionUID = 1L;

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }

    public String getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(String flightTime) {
        this.flightTime = flightTime;
    }

    public int getSeatAvailable() {
        return seatAvailable;
    }

    public void setSeatAvailable(int seatAvailable) {
        this.seatAvailable = seatAvailable;
    }

    public Flight(){}

    public Flight(String origin, String destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public Flight(int flightID, String origin, String destination, String flightDate, String flightTime, int seatAvailable) throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm");
        Date date1 = sdf1.parse(flightDate);
        Date date2 = sdf2.parse(flightTime);
        this.flightID = flightID;
        this.origin = origin;
        this.destination = destination;
        this.flightDate = sdf1.format(date1);
        this.flightTime = sdf2.format(date2);
        this.seatAvailable = seatAvailable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight ticket = (Flight) o;
        return flightID == ticket.flightID && seatAvailable == ticket.seatAvailable && Objects.equals(origin, ticket.origin) && Objects.equals(destination, ticket.destination) && Objects.equals(flightDate, ticket.flightDate) && Objects.equals(flightTime, ticket.flightTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightID, origin, destination, flightDate, flightTime, seatAvailable);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "flightID=" + flightID +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", flightDate='" + flightDate + '\'' +
                ", flightTime='" + flightTime + '\'' +
                ", seatAvailable=" + seatAvailable +
                '}';
    }
}
