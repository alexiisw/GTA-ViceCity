package com.carsforsell.data;


//ZURIK PART

import com.carsforsell.models.Contract;

import java.io.IOException;
import java.nio.file.Path;
public class ContractFileManager {
    private final Path file;

    public ContractFileManager(Path file) { this.file = file; }

    public void saveContract(Contract contract) throws IOException {
        // TODO: use instanceof to distinguish SalesContract from LeaseContract.
        // TODO: format ALL required common and contract-specific fields separated by '|'.
        // TODO: append one record using Files.writeString(..., CREATE, APPEND).
        // IMPORTANT: do NOT overwrite older records.
        throw new UnsupportedOperationException("MISSION 4: Implement contract persistence");
    }
}

