import java.util.ArrayList;
import java.util.Scanner;

// Represents a customer of the rental system
// Demonstrates: Inheritance (extends User), Aggregation (has ArrayList of Bookings)
public class Customer extends User {

    // Static counter for auto-generating customer IDs
    private static int idCounter = 100;

    private int customerId;
    private ArrayList<Booking> rentalHistory;   // Aggregation

    // Default constructor
    public Customer() {
        super();
        this.customerId = ++idCounter;
        this.rentalHistory = new ArrayList<>();
    }

    // Parameterized constructor
    public Customer(String name, String contactInfo) {
        super(++idCounter, name, contactInfo);
        this.customerId = userId;
        this.rentalHistory = new ArrayList<>();
    }

    // Register a new customer (interactive)
    public static Customer register(Scanner sc) {
        System.out.println("\n  === Register New Customer ===");
        System.out.print("  Enter Name        : ");
        String name = sc.nextLine().trim();
        System.out.print("  Enter Contact/Email: ");
        String contact = sc.nextLine().trim();
        Customer c = new Customer(name, contact);
        System.out.println("  Customer registered! ID: " + c.getCustomerId());
        return c;
    }

    // Add a completed booking to this customer's history
    public void addToHistory(Booking b) {
        rentalHistory.add(b);
    }

    // View this customer's booking history
    public void viewHistory() {
        if (rentalHistory.isEmpty()) {
            System.out.println("  No booking history found for " + name + ".");
            return;
        }
        System.out.println("\n  === Booking History for " + name + " ===");
        for (Booking b : rentalHistory) {
            b.displayBooking();
        }
    }

    // Cancel a specific booking by ID (returns true if found)
    public boolean cancelBooking(int bookingId) {
        for (Booking b : rentalHistory) {
            if (b.getBookingId() == bookingId) {
                b.cancelBooking();
                return true;
            }
        }
        return false;
    }

    // Find a booking by ID in this customer's history
    public Booking findBooking(int bookingId) {
        for (Booking b : rentalHistory) {
            if (b.getBookingId() == bookingId) return b;
        }
        return null;
    }

    // Implement abstract method from User
    @Override
    public void displayUser() {
        System.out.println("  [Customer] ID: " + customerId + " | Name: " + name + " | Contact: " + contactInfo);
    }

    // Override login message
    @Override
    public String login() {
        return "Customer " + name + " logged in.";
    }

    // Getters
    public int getCustomerId()                     { return customerId; }
    public ArrayList<Booking> getRentalHistory()   { return rentalHistory; }
}
