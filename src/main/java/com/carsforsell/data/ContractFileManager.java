package com.carsforsell.data;
//ADD
import com.carsforsell.models.Contract;
import com.carsforsell.models.SalesContract;
import com.carsforsell.models.LeaseContract;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class ContractFileManager {

    private final Path file;

    public ContractFileManager(Path file) {
        this.file = file;
    }

    public void saveContract(Contract contract) throws IOException {

        String record;

        // Identify which type of contract was received
        if (contract instanceof SalesContract) {

            SalesContract sale = (SalesContract) contract;

            record = String.join("|",
                    "SALE",
                    sale.getDate(),
                    sale.getCustomerName(),
                    sale.getCustomerEmail(),
                    String.valueOf(sale.getVehicle().getVin()),
                    String.valueOf(sale.getVehicle().getYear()),
                    sale.getVehicle().getMake(),
                    sale.getVehicle().getModel(),
                    sale.getVehicle().getVehicleType(),
                    sale.getVehicle().getColor(),
                    String.valueOf(sale.getVehicle().getOdometer()),
                    String.format("%.2f", sale.getVehicle().getPrice()),
                    String.format("%.2f", sale.getTotalPrice()),
                    String.format("%.2f", sale.getMonthlyPayment())
            );

        } else if (contract instanceof LeaseContract) {

            LeaseContract lease = (LeaseContract) contract;

            record = String.join("|",
                    "LEASE",
                    lease.getDate(),
                    lease.getCustomerName(),
                    lease.getCustomerEmail(),
                    String.valueOf(lease.getVehicle().getVin()),
                    String.valueOf(lease.getVehicle().getYear()),
                    lease.getVehicle().getMake(),
                    lease.getVehicle().getModel(),
                    lease.getVehicle().getVehicleType(),
                    lease.getVehicle().getColor(),
                    String.valueOf(lease.getVehicle().getOdometer()),
                    String.format("%.2f", lease.getVehicle().getPrice()),
                    String.format("%.2f", lease.getTotalPrice()),
                    String.format("%.2f", lease.getMonthlyPayment())
            );

        } else {
            throw new IllegalArgumentException("Unknown contract type");
        }

        // Append the new contract without overwriting existing records
        Files.writeString(
                file,
                record + System.lineSeparator(),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
        );
    }
}