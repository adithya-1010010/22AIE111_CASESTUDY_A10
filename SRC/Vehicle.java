// Represents a rentable vehicle in the system
// Demonstrates: Encapsulation, Constructors, Getters/Setters, toString()
public class Vehicle {

    // Private attributes (Encapsulation)
    private int vehicleId;
    private String model;
    private String type;        // e.g., Car, Bike, Truck
    private double ratePerDay;
    private boolean isAvailable;

    // Default constructor
    public Vehicle() {
        this.vehicleId = 0;
        this.model = "Unknown";
        this.type = "Unknown";
        this.ratePerDay = 0.0;
        this.isAvailable = true;
    }

    // Parameterized constructor
    public Vehicle(int vehicleId, String model, String type, double ratePerDay) {
        this.vehicleId = vehicleId;
        this.model = model;
        this.type = type;
        this.ratePerDay = ratePerDay;
        this.isAvailable = true;   // New vehicles are available by default
    }

    // Display all vehicle details in a formatted way
    public void displayVehicleDetails() {
        System.out.println("--------------------------------------------------");
        System.out.println("  Vehicle ID   : " + vehicleId);
        System.out.println("  Model        : " + model);
        System.out.println("  Type         : " + type);
        System.out.printf ("  Rate/Day     : Rs. %.2f%n", ratePerDay);
        System.out.println("  Availability : " + (isAvailable ? "Available" : "Booked"));
        System.out.println("--------------------------------------------------");
    }

    // Update availability status
    public void setAvailability(boolean status) {
        this.isAvailable = status;
    }

    // Get rate per day (useful for charge calculation)
    public double getRate() {
        return ratePerDay;
    }

    // toString() override for quick display
    @Override
    public String toString() {
        return String.format("[ID: %d] %s (%s) - Rs. %.2f/day - %s",
                vehicleId, model, type, ratePerDay,
                isAvailable ? "Available" : "Booked");
    }

    // ---- Getters and Setters ----
    public int getVehicleId()                  { return vehicleId; }
    public void setVehicleId(int id)           { this.vehicleId = id; }

    public String getModel()                   { return model; }
    public void setModel(String model)         { this.model = model; }

    public String getType()                    { return type; }
    public void setType(String type)           { this.type = type; }

    public double getRatePerDay()              { return ratePerDay; }
    public void setRatePerDay(double rate)     { this.ratePerDay = rate; }

    public boolean isAvailable()               { return isAvailable; }
}
