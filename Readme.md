# Car Rental Management System

A desktop application built with JavaFX and MySQL for managing car rentals, with separate dashboards for customers and administrators.

---

## Features

### User Dashboard
- **Browse Cars** — Home screen displays available cars as visual cards loaded from the database
- **Rent a Car** — Select from a table, confirm dates and name in a popup; marks the car unavailable on confirmation
- **Pre-Book a Car** — Same flow as renting but with a freely selectable future renting date (up to 1 month ahead)
- **Request a Car** — Submit a brand/model request that the admin can review

### Admin Dashboard
- **Add a Car** — Form with Car ID, brand, model, price, and an image upload; writes directly to the `Car` table
- **Delete a Car** — Select and remove a car from the inventory table
- **Sales Report** — Read-only text area showing all rows from `RentedCars`
- **Car Requests** — Collapsible table showing pending user requests
- **Home Charts** — Line chart (customer activity) and bar chart (income) on the home pane; wallet label shows the last recorded rent amount

---

## Tech Stack

| | |
|---|---|
| Language | Java 17 |
| UI Framework | JavaFX 17.0.6 |
| Database | MySQL (JDBC) |
| Build Tool | Maven 3.8.5 |
| Extra UI | ControlsFX 11.1.2 |

---

## Project Structure

Only the files that matter for understanding the code:

```
src/
├── main/
│   ├── java/com/example/carrentalmanagmentsystem/
│   │   ├── HelloApplication.java          # Entry point — loads UserDashboard.fxml
│   │   ├── HelloController.java           # Login screen: credential check, routing to admin vs user
│   │   ├── AdminDashboardController.java  # All admin actions (add, delete, reports, charts)
│   │   ├── UserDashboardController.java   # All user actions (browse, rent, pre-book, request)
│   │   ├── popupController.java           # Rental confirmation popup: date validation, DB writes
│   │   ├── CarCardController.java         # Sets data on individual car card components
│   │   ├── Database.java                  # All SQL — CRUD for cars, rentals, requests
│   │   ├── CarData.java                   # Model: carId, brand, model, price, status, image URL
│   │   ├── CarCard.java                   # Display model: image src, name, price (for home grid)
│   │   ├── Sales.java                     # Model: customer, car, totalRent, renting/returning dates
│   │   └── GetData.java                   # Static holder for the logged-in username across screens
│   └── resources/com/example/carrentalmanagmentsystem/
│       ├── Login.fxml
│       ├── AdminDashboard.fxml
│       ├── UserDashboard.fxml
│       ├── popup.fxml
│       ├── CarCard.fxml
│       └── CSS-Files/
│           ├── AdminDashboard.css
│           ├── UserDashboard.css
│           ├── loginStyle.css
│           └── popup.css
pom.xml                                    # Dependencies and JavaFX Maven plugin config
```

**Not worth looking at:** `target/`, `.idea/`, `.mvn/wrapper/`, `mvnw`, `mvnw.cmd`, `desktop.ini`, and the `classes/`, `css/`, `fxml/` folders containing `.txt` duplicates of the source files.

---

## Database Setup

Connects to MySQL at `localhost:3306`, database `hadi`. Credentials are hardcoded in `Database.java` (`root` / `hadi123`).

```sql
CREATE TABLE users (
    username VARCHAR(50),
    password VARCHAR(50)
);

CREATE TABLE Car (
    RegistrationNumber VARCHAR(50) PRIMARY KEY,
    Brand              VARCHAR(50),
    Model              VARCHAR(50),
    Price              DOUBLE,
    Status             VARCHAR(20),   -- 'Available' or 'Unavailable'
    Date               DATE,
    URL                VARCHAR(500)   -- local file URI to the car image
);

CREATE TABLE RentedCars (
    Name          VARCHAR(50),
    Registration  VARCHAR(50),
    TotalRent     DOUBLE,
    RentingDate   VARCHAR(20),
    ReturningDate VARCHAR(20)
);

CREATE TABLE request (
    Brand VARCHAR(50),
    Model VARCHAR(50)
);

-- Default admin account
INSERT INTO users VALUES ('admin', 'your_password');
```

---

## Getting Started

**Prerequisites:** Java 17+, Maven 3.8+, MySQL on `localhost:3306`

```bash
# 1. Clone and enter the project
git clone <repo-url> && cd OOP

# 2. Create the database and run the SQL above in your MySQL client

# 3. Add car images to src/main/resources/Images/
#    Name each file after the car model, e.g. BMW.png, Aventador.png

# 4. Run
mvn clean javafx:run
```

---

## Login Routing

| Username | Goes to |
|---|---|
| `admin` | Admin Dashboard |
| anything else | User Dashboard |

Passwords are checked in plain text against the `users` table.

---

## Key Behaviors

- Confirming a rental sets the car's `Status` to `Unavailable` in the `Car` table.
- Unavailable cars show a warning and cannot be selected for rent or pre-booking.
- Pre-bookings enable the renting date picker; regular rentals lock it to today.
- Maximum rental duration is 1 month.
- `popupController.totalrent` is a static field — it persists the last transaction amount and is read by the Admin dashboard's wallet label on next load.
