import java.util.ArrayList;

// Manages all vehicles in the rental system
// Demonstrates: Encapsulation, Aggregation (holds ArrayList<Vehicle>)
public class Inventory {

    private ArrayList<Vehicle> vehicleList;  // Aggregation
    private int totalCount;
    private int availableCount;

    // Constructor - initialises with some sample vehicles
    public Inventory() {
        vehicleList = new ArrayList<>();
        totalCount = 0;
        availableCount = 0;
        loadSampleVehicles();
    }

    // Pre-load some vehicles so the system isn't empty on first run
    private void loadSampleVehicles() {
        addVehicle(new Vehicle(1, "Toyota Innova",  "Car",   1500.0));
        addVehicle(new Vehicle(2, "Honda Activa",   "Bike",   300.0));
        addVehicle(new Vehicle(3, "Mahindra Bolero","SUV",   2000.0));
        addVehicle(new Vehicle(4, "Tata Ace",       "Truck", 2500.0));
        addVehicle(new Vehicle(5, "Royal Enfield",  "Bike",   500.0));
    }

    // Add a vehicle to inventory
    public void addVehicle(Vehicle v) {
        vehicleList.add(v);
        totalCount++;
        if (v.isAvailable()) availableCount++;
        System.out.println("  Vehicle added: " + v.getModel());
    }

    // Check if a specific vehicle ID is available
    public boolean checkAvailability(int vehicleId) {
        Vehicle v = findById(vehicleId);
        return v != null && v.isAvailable();
    }

    // Update availability counts (call after any status change)
    public void updateInventory() {
        availableCount = 0;
        for (Vehicle v : vehicleList) {
            if (v.isAvailable()) availableCount++;
        }
        totalCount = vehicleList.size();
    }

    // Return only the available vehicles
    public ArrayList<Vehicle> getAvailableVehicles() {
        ArrayList<Vehicle> available = new ArrayList<>();
        for (Vehicle v : vehicleList) {
            if (v.isAvailable()) available.add(v);
        }
        return available;
    }

    // Display full inventory with formatted output
    public void displayInventory() {
        updateInventory();
        System.out.println("\n  ============================================");
        System.out.println("               VEHICLE INVENTORY              ");
        System.out.println("  ============================================");
        System.out.println("  Total Vehicles    : " + totalCount);
        System.out.println("  Available         : " + availableCount);
        System.out.println("  Currently Booked  : " + (totalCount - availableCount));
        System.out.println("  --------------------------------------------");
        if (vehicleList.isEmpty()) {
            System.out.println("  No vehicles in inventory.");
        } else {
            for (Vehicle v : vehicleList) {
                v.displayVehicleDetails();
            }
        }
        System.out.println("  ============================================");
    }

    // Display only available vehicles
    public void displayAvailableVehicles() {
        ArrayList<Vehicle> available = getAvailableVehicles();
        System.out.println("\n  === Available Vehicles ===");
        if (available.isEmpty()) {
            System.out.println("  No vehicles are currently available.");
            return;
        }
        for (Vehicle v : available) {
            System.out.println("  " + v);
        }
    }

    // Find a vehicle by its ID; returns null if not found
    public Vehicle findById(int vehicleId) {
        for (Vehicle v : vehicleList) {
            if (v.getVehicleId() == vehicleId) return v;
        }
        return null;
    }

    // Find vehicles by type (Car, Bike, etc.)
    public void searchByType(String type) {
        System.out.println("\n  Search results for type: " + type);
        boolean found = false;
        for (Vehicle v : vehicleList) {
            if (v.getType().equalsIgnoreCase(type)) {
                System.out.println("  " + v);
                found = true;
            }
        }
        if (!found) System.out.println("  No vehicles found for type: " + type);
    }

    // Remove a vehicle by ID (Admin use)
    public boolean removeVehicle(int vehicleId) {
        Vehicle v = findById(vehicleId);
        if (v != null) {
            vehicleList.remove(v);
            updateInventory();
            return true;
        }
        return false;
    }

    // Getters
    public ArrayList<Vehicle> getVehicleList()  { return vehicleList; }
    public int getTotalCount()                  { return totalCount; }
    public int getAvailableCount()              { return availableCount; }
}
