// Stores all details about a single rental booking
// Demonstrates: Association (links Customer and Vehicle), Encapsulation
public class Booking {

    // ---- Static counter to auto-generate booking IDs ----
    private static int idCounter = 1000;

    // Private attributes
    private int bookingId;
    private String startDate;
    private String endDate;
    private Customer customer;   // Association with Customer
    private Vehicle vehicle;     // Association with Vehicle
    private String status;       // "Confirmed", "Cancelled", "Returned"

    // Parameterized constructor
    public Booking(String startDate, String endDate, Customer customer, Vehicle vehicle) {
        this.bookingId = ++idCounter;
        this.startDate = startDate;
        this.endDate = endDate;
        this.customer = customer;
        this.vehicle = vehicle;
        this.status = "Confirmed";
    }

    // Confirm the booking (mark vehicle unavailable)
    public void confirmBooking() {
        vehicle.setAvailability(false);
        this.status = "Confirmed";
        System.out.println("\n  Booking #" + bookingId + " confirmed successfully!");
    }

    // Cancel the booking (make vehicle available again)
    public void cancelBooking() {
        if (status.equals("Cancelled")) {
            System.out.println("  Booking is already cancelled.");
            return;
        }
        vehicle.setAvailability(true);
        this.status = "Cancelled";
        System.out.println("\n  Booking #" + bookingId + " has been cancelled.");
    }

    // Calculate duration in days between startDate and endDate
    // Dates expected as: dd/MM/yyyy
    public int getDuration() {
        try {
            java.time.format.DateTimeFormatter fmt =
                    java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
            java.time.LocalDate start = java.time.LocalDate.parse(startDate, fmt);
            java.time.LocalDate end   = java.time.LocalDate.parse(endDate, fmt);
            long days = java.time.temporal.ChronoUnit.DAYS.between(start, end);
            return (int) Math.abs(days);
        } catch (Exception e) {
            // Fallback: return 1 if parsing fails
            return 1;
        }
    }

    // Display booking summary
    public void displayBooking() {
        System.out.println("  ================================================");
        System.out.println("  Booking ID   : " + bookingId);
        System.out.println("  Customer     : " + customer.getName());
        System.out.println("  Vehicle      : " + vehicle.getModel() + " (" + vehicle.getType() + ")");
        System.out.println("  Start Date   : " + startDate);
        System.out.println("  End Date     : " + endDate);
        System.out.println("  Duration     : " + getDuration() + " day(s)");
        System.out.println("  Status       : " + status);
        System.out.println("  ================================================");
    }

    // ---- Getters and Setters ----
    public int getBookingId()              { return bookingId; }
    public String getStartDate()           { return startDate; }
    public String getEndDate()             { return endDate; }
    public Customer getCustomer()          { return customer; }
    public Vehicle getVehicle()            { return vehicle; }
    public String getStatus()              { return status; }
    public void setStatus(String status)   { this.status = status; }
}
