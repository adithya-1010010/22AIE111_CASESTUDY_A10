import java.util.ArrayList;
import java.util.Scanner;

// Represents a staff member who manages vehicles and processes returns
// Demonstrates: Inheritance (extends User), Polymorphism (overrides login/displayUser)
public class Staff extends User {

    private static int idCounter = 200;

    private int staffId;
    private String role;    // e.g., "Fleet Manager", "Receptionist"

    // Default constructor
    public Staff() {
        super();
        this.staffId = ++idCounter;
        this.role = "General Staff";
    }

    // Parameterized constructor
    public Staff(String name, String contactInfo, String role) {
        super(++idCounter, name, contactInfo);
        this.staffId = userId;
        this.role = role;
    }

    // ---- Vehicle Management ----

    // Add a new vehicle to inventory (interactive)
    public void addVehicle(Inventory inventory, Scanner sc) {
        System.out.println("\n  === Add New Vehicle ===");
        try {
            System.out.print("  Vehicle ID   : ");
            int id = Integer.parseInt(sc.nextLine().trim());

            // Prevent duplicate IDs
            if (inventory.findById(id) != null) {
                System.out.println("  Vehicle with ID " + id + " already exists.");
                return;
            }

            System.out.print("  Model        : ");
            String model = sc.nextLine().trim();

            System.out.print("  Type (Car/Bike/SUV/Truck): ");
            String type = sc.nextLine().trim();

            System.out.print("  Rate Per Day : ");
            double rate = Double.parseDouble(sc.nextLine().trim());

            Vehicle v = new Vehicle(id, model, type, rate);
            inventory.addVehicle(v);

        } catch (NumberFormatException e) {
            System.out.println("  Invalid input. Please enter numeric values where required.");
        }
    }

    // Update an existing vehicle's details
    public void updateVehicle(Inventory inventory, Scanner sc) {
        System.out.println("\n  === Update Vehicle Details ===");
        try {
            System.out.print("  Enter Vehicle ID to update: ");
            int id = Integer.parseInt(sc.nextLine().trim());

            Vehicle v = inventory.findById(id);
            if (v == null) {
                System.out.println("  Vehicle not found.");
                return;
            }

            System.out.print("  New Model (leave blank to keep '" + v.getModel() + "'): ");
            String model = sc.nextLine().trim();
            if (!model.isEmpty()) v.setModel(model);

            System.out.print("  New Type (leave blank to keep '" + v.getType() + "'): ");
            String type = sc.nextLine().trim();
            if (!type.isEmpty()) v.setType(type);

            System.out.print("  New Rate/Day (0 to keep " + v.getRatePerDay() + "): ");
            double rate = Double.parseDouble(sc.nextLine().trim());
            if (rate > 0) v.setRatePerDay(rate);

            System.out.println("  Vehicle updated successfully.");
            v.displayVehicleDetails();

        } catch (NumberFormatException e) {
            System.out.println("  Invalid input.");
        }
    }

    // Process a vehicle return: mark available, calculate charge, print invoice
    public void processReturn(ArrayList<Booking> allBookings, Scanner sc) {
        System.out.println("\n  === Process Vehicle Return ===");
        try {
            System.out.print("  Enter Booking ID to process return: ");
            int bookingId = Integer.parseInt(sc.nextLine().trim());

            Booking target = null;
            for (Booking b : allBookings) {
                if (b.getBookingId() == bookingId) {
                    target = b;
                    break;
                }
            }

            if (target == null) {
                System.out.println("  Booking ID not found.");
                return;
            }
            if (!target.getStatus().equals("Confirmed")) {
                System.out.println("  This booking cannot be returned (Status: " + target.getStatus() + ").");
                return;
            }

            // Mark vehicle available
            target.getVehicle().setAvailability(true);
            target.setStatus("Returned");

            System.out.println("  Vehicle '" + target.getVehicle().getModel() + "' returned successfully.");

            // Generate invoice
            RentalCharge charge = new RentalCharge(target);

            // Optional discount prompt
            System.out.print("  Apply discount? (Enter % or 0 to skip): ");
            double disc = Double.parseDouble(sc.nextLine().trim());
            if (disc > 0) charge.applyDiscount(disc);

            charge.generateInvoice();
            charge.markPaid();

        } catch (NumberFormatException e) {
            System.out.println("  Invalid input.");
        }
    }

    // Implement abstract method from User
    @Override
    public void displayUser() {
        System.out.println("  [Staff] ID: " + staffId + " | Name: " + name
                + " | Role: " + role + " | Contact: " + contactInfo);
    }

    // Override login
    @Override
    public String login() {
        return "Staff member " + name + " (" + role + ") logged in.";
    }

    // Getters / Setters
    public int getStaffId()          { return staffId; }
    public String getRole()          { return role; }
    public void setRole(String role) { this.role = role; }
}
