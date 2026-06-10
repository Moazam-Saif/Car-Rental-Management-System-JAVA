# Car Rental Management System

A desktop application built with **JavaFX** and **MySQL** that manages car rentals for both customers and administrators.

---

## Features

### User Side
- **Browse Cars** — View available cars as visual cards on the home dashboard
- **Rent a Car** — Select a car from the inventory and confirm a rental with date validation
- **Pre-Book a Car** — Reserve a car for a future date (up to 1 month in advance)
- **Request a Car** — Submit a request for a specific car brand/model not currently in inventory

### Admin Side
- **Add a Car** — Add new cars with registration number, brand, model, price, and an uploaded image
- **Delete a Car** — Remove cars from the inventory via a table view
- **Sales Reports** — View all rental transactions (customer name, car, total rent, renting/returning dates)
- **Car Requests** — View customer car requests in a collapsible table
- **Dashboard Charts** — Line chart for customer activity overview

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17 |
| UI Framework | JavaFX 17.0.6 |
| Database | MySQL |
| Build Tool | Maven 3.8.5 |
| UI Components | ControlsFX 11.1.2 |
| Testing | JUnit Jupiter 5.10.0 |

---

## Database Setup

The app connects to a MySQL database named `hadi` on `localhost:3306`.

Default credentials (hardcoded):
- **Username:** `root`
- **Password:** `hadi123`

### Required Tables

```sql
-- Users table
CREATE TABLE users (
    username VARCHAR(50),
    password VARCHAR(50)
);

-- Insert default admin and a sample user
INSERT INTO users VALUES ('admin', 'your_password');

-- Car inventory
CREATE TABLE Car (
    RegistrationNumber VARCHAR(50) PRIMARY KEY,
    Brand VARCHAR(50),
    Model VARCHAR(50),
    Price DOUBLE,
    Status VARCHAR(20),
    Date DATE,
    URL VARCHAR(500)
);

-- Rental records
CREATE TABLE RentedCars (
    Name VARCHAR(50),
    Registration VARCHAR(50),
    TotalRent DOUBLE,
    RentingDate VARCHAR(20),
    ReturningDate VARCHAR(20)
);

-- Car requests from users
CREATE TABLE request (
    Brand VARCHAR(50),
    Model VARCHAR(50)
);
```

---

## Getting Started

### Prerequisites
- Java 17+
- Maven 3.8+
- MySQL running on `localhost:3306`

### Steps

1. **Clone the repository**
   ```bash
   git clone <repo-url>
   cd OOP
   ```

2. **Set up the database** — Run the SQL statements above in your MySQL client.

3. **Add car images** — Place car images in `src/main/resources/Images/`. Images should be named after the car model (e.g. `BMW.png`, `Aventador.png`).

4. **Run the app**
   ```bash
   mvn clean javafx:run
   ```

---

## Project Structure

```
src/main/java/com/example/carrentalmanagmentsystem/
├── HelloApplication.java          # Entry point
├── HelloController.java           # Login screen controller
├── AdminDashboardController.java  # Admin panel logic
├── UserDashboardController.java   # User panel logic
├── popupController.java           # Rental confirmation popup
├── CarCardController.java         # Individual car card UI
├── Database.java                  # All DB operations
├── CarData.java                   # Car model class
├── CarCard.java                   # Car card display model
├── Sales.java                     # Rental/sales record model
└── GetData.java                   # Static session data (username)

src/main/resources/com/example/carrentalmanagmentsystem/
├── Login.fxml
├── AdminDashboard.fxml
├── UserDashboard.fxml
├── popup.fxml
└── CarCard.fxml

src/main/resources/
├── CSS-Files/
│   ├── AdminDashboard.css
│   ├── UserDashboard.css
│   ├── loginStyle.css
│   └── popup.css
└── Images/
    └── (car images go here)
```

---

## Login

| Role | Username | Notes |
|---|---|---|
| Admin | `admin` | Redirects to Admin Dashboard |
| User | any other username | Redirects to User Dashboard |

Passwords are stored and checked against the `users` table in plain text.

---

## Notes

- Car availability is automatically set to `Unavailable` after a rental is confirmed.
- Pre-bookings allow selecting a future renting date; regular rentals default to today.
- Rentals are limited to a maximum of 1 month duration.
- The `totalrent` field in `popupController` is a static variable — it tracks the last transaction and is displayed on the Admin home dashboard.
