# 22AIE111_CASESTUDY_A10

# Vehicle Rental Management System

A Java-based Object-Oriented Programming project developed as part of the course **22AIE111 – Object Oriented Programming in Java**.

---

## Team Members

| Roll No | Name | Role |
|----------|--------------------------|------------------------------|
| AM.SC.U4AIE25002 | Adithya R | Project Lead / Backend Logic |
| AM.SC.U4AIE25041 | Ananuay Krishna Menon | UML & Documentation |
| AM.SC.U4AIE25057 | Jeevan Menoj | Booking & Inventory Module |
| AM.SC.U4AIE25029 | Abhijith P | Testing & Charge Calculation |

---

## Problem Description

Manual vehicle rental systems often lead to:
- Booking overlaps
- Difficulty tracking vehicle availability
- Errors in rental charge calculation
- Poor record maintenance

The Vehicle Rental Management System solves these issues by digitizing the rental workflow. The system allows customers to search and book vehicles, staff to manage rentals and returns, and admins to monitor the entire platform efficiently.

---

## Features

- Vehicle Search
- Booking Management
- Rental Charge Calculation
- Vehicle Availability Tracking
- Booking History
- Vehicle Return Processing
- Admin Report Generation

---

## Tools / Technologies Used

- Java
- Object-Oriented Programming (OOP)
- UML Diagrams
- IntelliJ IDEA / VS Code
- JDK 17+

---

## Project Structure

```text
VehicleRentalManagementSystem/
│
├── Main.java
├── Vehicle.java
├── Customer.java
├── Booking.java
├── Staff.java
├── Admin.java
├── RentalCharge.java
├── Inventory.java
└── README.md
```

---

## How to Run the Code

### Step 1: Compile the Project

```bash
javac *.java
```

### Step 2: Run the Program

```bash
java Main
```

---

## Sample Input / Output

### Sample Input

```text
1. Search Vehicle
2. Make Booking
3. Return Vehicle

Enter Choice: 2
Enter Customer Name: Adithya
Enter Vehicle Type: SUV
Enter Rental Days: 3
```

### Sample Output

```text
Vehicle Available: Hyundai Creta
Booking Confirmed Successfully!

Rental Charge:
Rate Per Day: ₹2500
Total Amount: ₹7500

Invoice Generated Successfully.
```

---

## Main Classes Used

| Class | Purpose |
|--------|----------|
| Vehicle | Stores vehicle details and availability |
| Customer | Handles customer operations |
| Booking | Manages rental bookings |
| Staff | Handles vehicle updates and returns |
| Admin | Manages reports and users |
| RentalCharge | Calculates billing and invoices |
| Inventory | Tracks available vehicles |

---

## Academic Information

**Course:** 22AIE111 – Object Oriented Programming in Java  
**Department:** Department of Computer Science and Engineering  
**Institution:** Amrita School of Computing, Amritapuri Campus
