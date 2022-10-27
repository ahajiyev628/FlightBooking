package StepProject.DAO;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class BookingApp implements Serializable {
    private String passengerName;
    private String passengerSurname;
    private int bookingID;
    private String bookingOrigin;
    private String bookingDestination;
    private String bookingDate;
    private String bookingTime;

    private static final long serialVersionUID = 1L;
    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassengerSurname() {
        return passengerSurname;
    }

    public void setPassengerSurname(String passengerSurname) {
        this.passengerSurname = passengerSurname;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public String getBookingOrigin() {
        return bookingOrigin;
    }

    public void setBookingOrigin(String bookingOrigin) {
        this.bookingOrigin = bookingOrigin;
    }

    public String getBookingDestination() {
        return bookingDestination;
    }

    public void setBookingDestination(String bookingDestination) {
        this.bookingDestination = bookingDestination;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }

    public BookingApp() {
    }

    public BookingApp(String passengerName, String passengerSurname, int bookingID, String bookingOrigin, String bookingDestination, String bookingDate, String bookingTime) throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm");
        Date date1 = sdf1.parse(bookingDate);
        Date date2 = sdf2.parse(bookingTime);
        this.passengerName = passengerName;
        this.passengerSurname = passengerSurname;
        this.bookingID = bookingID;
        this.bookingOrigin = bookingOrigin;
        this.bookingDestination = bookingDestination;
        this.bookingDate = sdf1.format(date1);
        this.bookingTime = sdf2.format(date2);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingApp that = (BookingApp) o;
        return bookingID == that.bookingID && Objects.equals(passengerName, that.passengerName) && Objects.equals(passengerSurname, that.passengerSurname) && Objects.equals(bookingOrigin, that.bookingOrigin) && Objects.equals(bookingDestination, that.bookingDestination) && Objects.equals(bookingDate, that.bookingDate) && Objects.equals(bookingTime, that.bookingTime);
    }
    @Override
    public int hashCode() {
        return Objects.hash(passengerName, passengerSurname, bookingID, bookingOrigin, bookingDestination, bookingDate, bookingTime);
    }
    @Override
    public String toString() {
        return "BookingApp{" +
                "passengerName='" + passengerName + '\'' +
                ", passengerSurname='" + passengerSurname + '\'' +
                ", bookingID=" + bookingID +
                ", bookingOrigin='" + bookingOrigin + '\'' +
                ", bookingDestination='" + bookingDestination + '\'' +
                ", bookingDate='" + bookingDate + '\'' +
                ", bookingTime='" + bookingTime + '\'' +
                '}';
    }
}
