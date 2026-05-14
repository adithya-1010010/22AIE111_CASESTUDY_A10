// Base class for all users in the system (Customer, Staff, Admin)
// Demonstrates: Inheritance, Abstraction, Encapsulation
public abstract class User {

    // Protected so subclasses can access directly
    protected int userId;
    protected String name;
    protected String contactInfo;

    // Default constructor
    public User() {
        this.userId = 0;
        this.name = "Unknown";
        this.contactInfo = "N/A";
    }

    // Parameterized constructor
    public User(int userId, String name, String contactInfo) {
        this.userId = userId;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    // Abstract method - must be implemented by subclasses (Abstraction)
    public abstract void displayUser();

    // Common login stub (Polymorphism via override in subclasses)
    public String login() {
        return name + " logged in.";
    }

    // Getters and Setters
    public int getUserId()             { return userId; }
    public void setUserId(int id)      { this.userId = id; }

    public String getName()            { return name; }
    public void setName(String name)   { this.name = name; }

    public String getContactInfo()             { return contactInfo; }
    public void setContactInfo(String info)    { this.contactInfo = info; }
}
