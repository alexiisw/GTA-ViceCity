# ViceCityMotors – Dealership Management System

## Project Description

ViceCityMotors is a Java dealership management application that allows users to manage vehicle inventory and create sales and lease contracts.

Users can select a vehicle by VIN, enter customer information, choose between purchasing or leasing, and generate contracts with automatically calculated pricing.

The project demonstrates Object-Oriented Programming (OOP), inheritance, abstraction, polymorphism, encapsulation, and file persistence.

## Project Directory Structure

The project is organized into packages to separate data management, business models, and user interaction.

```text
ViceCityMotors/
│
├── .idea/
├── .mvn/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.carsforsell/
│   │   │       │
│   │   │       ├── data/
│   │   │       │   ├── ContractFileManager.java
│   │   │       │   ├── contracts.csv
│   │   │       │   ├── inventory.csv
│   │   │       │   └── InventoryFileManager.java
│   │   │       │
│   │   │       ├── models/
│   │   │       │   ├── Contract.java
│   │   │       │   ├── LeaseContract.java
│   │   │       │   ├── SalesContract.java
│   │   │       │   └── Vehicle.java
│   │   │       │
│   │   │       ├── UI/
│   │   │       │   └── UserInterface.java
│   │   │       │
│   │   │       └── Main.java
│   │   │
│   │   └── resources/
│   │
│   └── test/
│
├── target/
├── .gitignore
└── pom.xml
```

### Package Responsibilities

| Package / File | Responsibility |
|---|---|
| `data` | Handles inventory and contract file operations. |
| `models` | Contains vehicle and contract classes. |
| `UI` | Handles user interaction and application menus. |
| `Main.java` | Entry point of the application. |

## Application Features

- View dealership inventory.
- Search for vehicles using their VIN.
- Sell vehicles to customers.
- Lease eligible vehicles.
- Collect customer information.
- Calculate sales and lease pricing.
- Calculate monthly payments.
- Save completed contracts to a CSV file.
- Append new contracts without overwriting existing records.

## Contract Types

### Sales Contract

A sales contract includes:

- Sales tax: 5% of the vehicle price.
- Recording fee: $100.
- Processing fee:
  - $295 for vehicles under $10,000.
  - $495 for vehicles costing $10,000 or more.
- Financing option (Yes/No).

**Financing Rules**

| Vehicle Price | Interest Rate | Loan Term |
|---|---:|---:|
| $10,000 or more | 4.25% | 48 months |
| Under $10,000 | 5.25% | 24 months |

If the customer chooses not to finance, the monthly payment is $0.

### Lease Contract

A lease contract includes:

- Expected ending value: 50% of the original vehicle price.
- Lease fee: 7% of the original vehicle price.
- Financing rate: 4.0%.
- Lease term: 36 months.

**Lease Eligibility**

Vehicles older than three years cannot be leased.

## Object-Oriented Design

### Contract (Abstract Class)

The parent class containing information shared by sales and lease contracts.

- Contract date
- Customer name
- Customer email
- Vehicle sold
- Total price
- Monthly payment

Defines abstract methods:
- `getTotalPrice()`
- `getMonthlyPayment()`

### SalesContract

Extends `Contract` and contains sales-specific information.

- Sales tax amount
- Recording fee
- Processing fee
- Finance option

Overrides:
- `getTotalPrice()`
- `getMonthlyPayment()`

### LeaseContract

Extends `Contract` and contains lease-specific information.

- Expected ending value
- Lease fee

Overrides:
- `getTotalPrice()`
- `getMonthlyPayment()`

### Vehicle

Represents a vehicle in the dealership inventory, containing information such as VIN, year, make, model, vehicle type, color, odometer, and price.

### ContractFileManager

Responsible for saving completed sales and lease contracts to the contracts CSV file.

### InventoryFileManager

Responsible for reading and managing dealership vehicle inventory data.

### UserInterface

Handles the application's menus and communication with the user.

### Main

Starts the application.

## Data Persistence

The application uses pipe-separated values (`|`) to store vehicle inventory and contract information.

When a customer completes a sale or lease, the application appends the contract to `contracts.csv`.

Both sales and lease contracts are stored in the same file.

Existing contract records are preserved when new contracts are added.

## Technologies Used

- Java
- Object-Oriented Programming
- Maven
- File I/O
- CSV and pipe-separated data files
- IntelliJ IDEA
- Git and GitHub

## Learning Objectives

This project demonstrates practical experience with:

- Abstraction
- Inheritance
- Polymorphism
- Encapsulation
- Method overriding
- Constructors and getters/setters
- File reading and writing
- Data persistence
- Business logic and financial calculations

---

**Project Name:** ViceCityMotors  
**Language:** Java  
**Build Tool:** Maven  
**IDE:** IntelliJ IDEA
