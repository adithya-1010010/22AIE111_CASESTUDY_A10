import java.util.ArrayList;

// Represents the system administrator with full access
// Demonstrates: Inheritance (extends User), Polymorphism
public class Admin extends User {

    private int adminId;
    private String accessLevel;   // e.g., "Super", "Limited"

    // Hardcoded admin credentials for simple login
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "1234";

    // Constructor
    public Admin(String name, String contactInfo, String accessLevel) {
        super(1, name, contactInfo);
        this.adminId = 1;
        this.accessLevel = accessLevel;
    }

    // Simple credential check
    public static boolean authenticate(String username, String password) {
        return ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password);
    }

    // View all customers registered in the system
    public void manageUsers(ArrayList<Customer> customers, ArrayList<Staff> staffList) {
        System.out.println("\n  === Registered Customers ===");
        if (customers.isEmpty()) {
            System.out.println("  No customers registered.");
        } else {
            for (Customer c : customers) c.displayUser();
        }
        System.out.println("\n  === Staff Members ===");
        if (staffList.isEmpty()) {
            System.out.println("  No staff members found.");
        } else {
            for (Staff s : staffList) s.displayUser();
        }
    }

    // View all bookings across the system
    public void viewAllBookings(ArrayList<Booking> bookings) {
        System.out.println("\n  === All System Bookings ===");
        if (bookings.isEmpty()) {
            System.out.println("  No bookings found.");
            return;
        }
        for (Booking b : bookings) {
            b.displayBooking();
        }
    }

    // Generate a summary report of the system
    public void generateReport(ArrayList<Booking> bookings, Inventory inventory) {
        System.out.println("\n  ============================================");
        System.out.println("            SYSTEM REPORT - SUMMARY          ");
        System.out.println("  ============================================");

        inventory.updateInventory();
        System.out.println("  Total Vehicles   : " + inventory.getTotalCount());
        System.out.println("  Available        : " + inventory.getAvailableCount());
        System.out.println("  Currently Booked : " + (inventory.getTotalCount() - inventory.getAvailableCount()));
        System.out.println("  --------------------------------------------");

        int confirmed = 0, cancelled = 0, returned = 0;
        double totalRevenue = 0;

        for (Booking b : bookings) {
            switch (b.getStatus()) {
                case "Confirmed": confirmed++;  break;
                case "Cancelled": cancelled++;  break;
                case "Returned":  returned++;   break;
            }
            // Estimate revenue from returned bookings
            if (b.getStatus().equals("Returned")) {
                totalRevenue += b.getDuration() * b.getVehicle().getRate();
            }
        }

        System.out.println("  Total Bookings   : " + bookings.size());
        System.out.println("  Confirmed        : " + confirmed);
        System.out.println("  Cancelled        : " + cancelled);
        System.out.println("  Returned         : " + returned);
        System.out.printf ("  Est. Revenue     : Rs. %.2f%n", totalRevenue);
        System.out.println("  ============================================\n");
    }

    // Implement abstract method from User
    @Override
    public void displayUser() {
        System.out.println("  [Admin] ID: " + adminId + " | Name: " + name
                + " | Access: " + accessLevel);
    }

    // Override login
    @Override
    public String login() {
        return "Admin " + name + " logged in with " + accessLevel + " access.";
    }

    // Getters
    public int getAdminId()          { return adminId; }
    public String getAccessLevel()   { return accessLevel; }
}
