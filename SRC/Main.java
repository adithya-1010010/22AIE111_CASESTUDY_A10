import java.util.ArrayList;
import java.util.Scanner;

/**
 * ============================================================
 *   VEHICLE RENTAL MANAGEMENT SYSTEM
 *   Console-based Java Application
 *   Demonstrates: OOP, Inheritance, Polymorphism, Collections,
 *                 Exception Handling, Encapsulation, Abstraction
 * ============================================================
 */
public class Main {

    // ---- Global Data Stores ----
    static Inventory inventory = new Inventory();
    static ArrayList<Customer> customers   = new ArrayList<>();
    static ArrayList<Booking>  allBookings = new ArrayList<>();
    static ArrayList<Staff>    staffList   = new ArrayList<>();
    static Admin admin = new Admin("System Admin", "admin@rental.com", "Super");
    static Scanner sc = new Scanner(System.in);

    // ---- Entry Point ----
    public static void main(String[] args) {
        printBanner();

        // Add a default staff member
        staffList.add(new Staff("Ravi Kumar", "9876543210", "Fleet Manager"));

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("  Enter choice: ");
            System.out.println();

            switch (choice) {
                case 1  -> registerCustomer();
                case 2  -> addVehicle();
                case 3  -> viewAvailableVehicles();
                case 4  -> makeBooking();
                case 5  -> cancelBooking();
                case 6  -> returnVehicle();
                case 7  -> viewBookingHistory();
                case 8  -> viewAllBookings();
                case 9  -> generateReport();
                case 10 -> searchByType();
                case 11 -> viewInventory();
                case 0  -> {
                    System.out.println("  Thank you for using Vehicle Rental Management System. Goodbye!");
                    running = false;
                }
                default -> System.out.println("  Invalid choice. Please try again.");
            }

            if (running) pause();
        }
        sc.close();
    }

    // ============================================================
    //  MENU OPTION HANDLERS
    // ============================================================

    // 1. Register a new customer
    static void registerCustomer() {
        Customer c = Customer.register(sc);
        customers.add(c);
    }

    // 2. Add a vehicle (Staff/Admin action)
    static void addVehicle() {
        if (staffList.isEmpty()) {
            System.out.println("  No staff members available to add vehicles.");
            return;
        }
        staffList.get(0).addVehicle(inventory, sc);
    }

    // 3. View available vehicles
    static void viewAvailableVehicles() {
        inventory.displayAvailableVehicles();
    }

    // 4. Make a new booking
    static void makeBooking() {
        if (customers.isEmpty()) {
            System.out.println("  No customers registered. Please register first (Option 1).");
            return;
        }

        // Display available vehicles
        inventory.displayAvailableVehicles();
        ArrayList<Vehicle> available = inventory.getAvailableVehicles();
        if (available.isEmpty()) return;

        // Select customer
        System.out.println("\n  Registered Customers:");
        for (Customer c : customers) c.displayUser();

        int custId = readInt("  Enter Customer ID: ");
        Customer selectedCustomer = findCustomer(custId);
        if (selectedCustomer == null) {
            System.out.println("  Customer not found.");
            return;
        }

        // Select vehicle
        int vehId = readInt("  Enter Vehicle ID to book: ");
        if (!inventory.checkAvailability(vehId)) {
            System.out.println("  Vehicle is not available or does not exist.");
            return;
        }
        Vehicle selectedVehicle = inventory.findById(vehId);

        // Dates
        System.out.print("  Start Date (dd/MM/yyyy): ");
        String startDate = sc.nextLine().trim();
        System.out.print("  End Date   (dd/MM/yyyy): ");
        String endDate = sc.nextLine().trim();

        // Create and confirm booking
        Booking booking = new Booking(startDate, endDate, selectedCustomer, selectedVehicle);
        booking.confirmBooking();
        selectedCustomer.addToHistory(booking);
        allBookings.add(booking);
        inventory.updateInventory();

        // Show preliminary charge estimate
        RentalCharge charge = new RentalCharge(booking);
        System.out.printf("  Estimated Charge: Rs. %.2f for %d day(s)%n",
                charge.getTotalAmount(), booking.getDuration());
    }

    // 5. Cancel a booking
    static void cancelBooking() {
        if (allBookings.isEmpty()) {
            System.out.println("  No bookings exist to cancel.");
            return;
        }
        int bookingId = readInt("  Enter Booking ID to cancel: ");
        boolean found = false;
        for (Booking b : allBookings) {
            if (b.getBookingId() == bookingId) {
                b.cancelBooking();
                inventory.updateInventory();
                found = true;
                break;
            }
        }
        if (!found) System.out.println("  Booking ID not found.");
    }

    // 6. Return vehicle (Staff action)
    static void returnVehicle() {
        if (allBookings.isEmpty()) {
            System.out.println("  No active bookings.");
            return;
        }
        staffList.get(0).processReturn(allBookings, sc);
        inventory.updateInventory();
    }

    // 7. View a specific customer's booking history
    static void viewBookingHistory() {
        if (customers.isEmpty()) {
            System.out.println("  No customers registered.");
            return;
        }
        System.out.println("  Registered Customers:");
        for (Customer c : customers) c.displayUser();
        int custId = readInt("  Enter Customer ID: ");
        Customer c = findCustomer(custId);
        if (c == null) {
            System.out.println("  Customer not found.");
            return;
        }
        c.viewHistory();
    }

    // 8. Admin: View all bookings
    static void viewAllBookings() {
        admin.viewAllBookings(allBookings);
    }

    // 9. Admin: Generate system report
    static void generateReport() {
        admin.generateReport(allBookings, inventory);
    }

    // 10. Search vehicles by type
    static void searchByType() {
        System.out.print("  Enter vehicle type to search (Car/Bike/SUV/Truck): ");
        String type = sc.nextLine().trim();
        inventory.searchByType(type);
    }

    // 11. View full inventory
    static void viewInventory() {
        inventory.displayInventory();
    }

    // ============================================================
    //  HELPER METHODS
    // ============================================================

    // Find a customer by their ID
    static Customer findCustomer(int customerId) {
        for (Customer c : customers) {
            if (c.getCustomerId() == customerId) return c;
        }
        return null;
    }

    // Read an integer safely with exception handling
    static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  Invalid input. Please enter a whole number.");
            }
        }
    }

    // Pause and wait for user to press Enter before showing menu again
    static void pause() {
        System.out.print("\n  Press ENTER to continue...");
        sc.nextLine();
    }

    // ============================================================
    //  UI: BANNER AND MENU
    // ============================================================

    static void printBanner() {
        System.out.println("  ============================================================");
        System.out.println("        VEHICLE RENTAL MANAGEMENT SYSTEM                      ");
        System.out.println("        Console Application  |  Java OOP Project              ");
        System.out.println("  ============================================================");
        System.out.println("  System initialised. Sample vehicles loaded.\n");
    }

    static void printMenu() {
        System.out.println("  ============================================================");
        System.out.println("                        MAIN MENU                             ");
        System.out.println("  ============================================================");
        System.out.println("   1.  Register Customer");
        System.out.println("   2.  Add Vehicle");
        System.out.println("   3.  View Available Vehicles");
        System.out.println("   4.  Make Booking");
        System.out.println("   5.  Cancel Booking");
        System.out.println("   6.  Return Vehicle");
        System.out.println("   7.  View Booking History (by Customer)");
        System.out.println("   8.  View All Bookings");
        System.out.println("   9.  Generate Report");
        System.out.println("   10. Search Vehicles by Type");
        System.out.println("   11. View Full Inventory");
        System.out.println("   0.  Exit");
        System.out.println("  ============================================================");
    }
}
