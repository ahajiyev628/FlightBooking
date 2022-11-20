package StepProject.Main;

import StepProject.Main.BookingMenu;

import java.io.*;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, ParseException {
        BookingMenu app = new BookingMenu();
        app.menu();
    }
}