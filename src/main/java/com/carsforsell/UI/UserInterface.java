package com.carsforsell.UI;

import com.carsforsell.data.ContractFileManager;
import com.carsforsell.data.InventoryFileManager;
import com.carsforsell.models.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private static final Scanner scanner = new Scanner(System.in);
    private final InventoryFileManager inventoryFileManager;
    private final ContractFileManager contractFileManager;
    private List<Vehicle> inventory;

    public UserInterface(Path dataDirectory) throws IOException {
        inventoryFileManager = new InventoryFileManager(dataDirectory.resolve("inventory.csv"));
        contractFileManager = new ContractFileManager(dataDirectory.resolve("contracts.csv"));
        inventory = new ArrayList<>(inventoryFileManager.loadVehicles());
    }

    public void display() {
        boolean running = true;
        while (running) {
            System.out.println("\n========== LOS SANTOS MOTORS ==========");
            System.out.println("1. Browse showroom");
            System.out.println("2. Search vehicles by VIN");
            System.out.println("3. Add vehicle");
            System.out.println("4. Remove vehicle");
            System.out.println("5. Sell / Lease vehicle [WORKSHOP MISSION]");
            System.out.println("0. Exit");
            String choice = ask("Choose: ");
            try {
                switch (choice) {
                    case "1" -> browse();
                    case "2" -> search();
                    case "3" -> addVehicle();
                    case "4" -> removeVehicle();
                    case "5" -> sellOrLeaseVehicle();
                    case "0" -> running = false;
                    default -> System.out.println("Invalid option.");
                }
            } catch (IOException ex) {
                System.out.println("File error: " + ex.getMessage());
            } catch (IllegalArgumentException ex) {
                System.out.println("Invalid input: " + ex.getMessage());
            }
        }
    }

    private static String ask(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private void browse() {
        if (inventory.isEmpty()) { System.out.println("Showroom is empty."); return; }
        inventory.forEach(System.out::println);
    }

    private Vehicle findByVin(String vin) {
        return inventory.stream().filter(v -> v.getVin().equalsIgnoreCase(vin)).findFirst().orElse(null);
    }

    private void search() {
        Vehicle found = findByVin(ask("VIN: "));
        System.out.println(found == null ? "Vehicle not found." : found);
    }

    private void addVehicle() throws IOException {
        String vin = ask("VIN: ");
        if (vin.isBlank() || vin.contains("|")) throw new IllegalArgumentException("Invalid VIN.");
        if (findByVin(vin) != null) { System.out.println("VIN already exists."); return; }
        int year = Integer.parseInt(ask("Year: "));
        String make = safeField("Make: ");
        String model = safeField("Model: ");
        String type = safeField("Vehicle type: ");
        String color = safeField("Color: ");
        int odometer = Integer.parseInt(ask("Odometer: "));
        double price = Double.parseDouble(ask("Price: "));
        if (year < 1900 || year > java.time.Year.now().getValue() + 1 || odometer < 0 || !Double.isFinite(price) || price < 0) {
            throw new IllegalArgumentException("Invalid vehicle year, mileage, or price.");
        }
        List<Vehicle> updated = new ArrayList<>(inventory);
        updated.add(new Vehicle(vin, year, make, model, type, color, odometer, price));
        inventoryFileManager.saveVehicles(updated);
        inventory = updated;
        System.out.println("Vehicle added.");
    }

    private String safeField(String label) {
        String field = ask(label);
        if (field.isBlank() || field.contains("|")) throw new IllegalArgumentException("Invalid field value.");
        return field;
    }

    private void removeVehicle() throws IOException {
        Vehicle found = findByVin(ask("VIN: "));
        if (found == null) { System.out.println("Vehicle not found."); return; }
        List<Vehicle> updated = new ArrayList<>(inventory);
        updated.remove(found);
        inventoryFileManager.saveVehicles(updated);
        inventory = updated;
        System.out.println("Vehicle removed.");
    }

    private void sellOrLeaseVehicle() throws IOException {

        // 1. Ask for VIN
        String vin = UserInterface.ask("Enter VIN: ");

        Vehicle vehicle = dealership.findByVin(vin);

        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        // 2. Customer information
        String customerName = UserInterface.ask("Customer Name: ");
        String customerEmail = UserInterface.ask("Customer Email: ");

        String contractType =
                UserInterface.ask("Sale or Lease? (S/L): ").trim().toUpperCase();

        Contract contract;

        // 3. Lease validation
        if (contractType.equals("L")) {

            int currentYear = java.time.Year.now().getValue();

            if (currentYear - vehicle.getYear() > 3) {
                System.out.println("Vehicle is too old to lease.");
                return;
            }

            contract = new LeaseContract(
                    currentDate(),
                    customerName,
                    customerEmail,
                    vehicle
            );

        } else if (contractType.equals("S")) {

            // 4. Financing
            boolean finance =
                    UserInterface.askYesNo("Would you like financing? (Y/N): ");

            contract = new SalesContract(
                    currentDate(),
                    customerName,
                    customerEmail,
                    vehicle,
                    finance
            );

        } else {
            System.out.println("Invalid contract type.");
            return;
        }

        // 6. Display pricing
        System.out.printf("Total Price: $%,.2f%n",
                contract.getTotalPrice());

        System.out.printf("Monthly Payment: $%,.2f%n",
                contract.getMonthlyPayment());

        // 7. Save Contract
        contractFileManager.saveContract(contract);

        // 8. Remove vehicle from inventory
        dealership.removeVehicle(vehicle);

        dealershipFileManager.saveDealership(dealership);

        System.out.println("Contract completed successfully.");
    }

}

