// Handles charge calculation and invoice generation for a booking
// Demonstrates: Encapsulation, Association (linked to Booking)
public class RentalCharge {

    private static int chargeCounter = 500;

    private int chargeId;
    private Booking booking;         // Association with Booking
    private double totalAmount;
    private String paymentStatus;    // "Paid" or "Pending"
    private double discountPercent;

    // Constructor
    public RentalCharge(Booking booking) {
        this.chargeId = ++chargeCounter;
        this.booking = booking;
        this.discountPercent = 0.0;
        this.paymentStatus = "Pending";
        calculateCharge();
    }

    // Calculate total rental charge
    public void calculateCharge() {
        int days = booking.getDuration();
        double rate = booking.getVehicle().getRate();
        double gross = days * rate;
        double discount = gross * (discountPercent / 100.0);
        this.totalAmount = gross - discount;
    }

    // Apply a discount percentage (e.g., 10 for 10%)
    public void applyDiscount(double percent) {
        if (percent < 0 || percent > 100) {
            System.out.println("  Invalid discount percentage.");
            return;
        }
        this.discountPercent = percent;
        calculateCharge();
        System.out.printf("  Discount of %.1f%% applied. New total: Rs. %.2f%n",
                percent, totalAmount);
    }

    // Generate and print a formatted invoice
    public void generateInvoice() {
        System.out.println("\n  ============================================");
        System.out.println("        VEHICLE RENTAL MANAGEMENT SYSTEM      ");
        System.out.println("                    INVOICE                   ");
        System.out.println("  ============================================");
        System.out.println("  Charge ID     : " + chargeId);
        System.out.println("  Booking ID    : " + booking.getBookingId());
        System.out.println("  Customer      : " + booking.getCustomer().getName());
        System.out.println("  Contact       : " + booking.getCustomer().getContactInfo());
        System.out.println("  Vehicle       : " + booking.getVehicle().getModel());
        System.out.println("  Type          : " + booking.getVehicle().getType());
        System.out.printf ("  Rate/Day      : Rs. %.2f%n", booking.getVehicle().getRate());
        System.out.println("  Start Date    : " + booking.getStartDate());
        System.out.println("  End Date      : " + booking.getEndDate());
        System.out.println("  Duration      : " + booking.getDuration() + " day(s)");
        if (discountPercent > 0)
            System.out.printf("  Discount      : %.1f%%%n", discountPercent);
        System.out.println("  --------------------------------------------");
        System.out.printf ("  TOTAL AMOUNT  : Rs. %.2f%n", totalAmount);
        System.out.println("  Payment Status: " + paymentStatus);
        System.out.println("  ============================================\n");
    }

    // Mark as paid
    public void markPaid() {
        this.paymentStatus = "Paid";
        System.out.println("  Payment of Rs. " + String.format("%.2f", totalAmount) + " recorded. Thank you!");
    }

    // Getters
    public double getTotalAmount()      { return totalAmount; }
    public String getPaymentStatus()    { return paymentStatus; }
    public int getChargeId()            { return chargeId; }
}
