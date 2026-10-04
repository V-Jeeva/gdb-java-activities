package com.gdb.logging;

import com.gdb.command.TransactionCommand;
import java.util.List;
import java.io.IOException;

public class FileLogDestination implements LogDestination {
    private TransactionLog log;

    public FileLogDestination() {
        this.log = new TransactionLog();
    }

    @Override
    public void write(TransactionCommand cmd) {
        try {
            log.log(cmd);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file log: " + e.getMessage(), e);
        }
    }

    @Override
    public List<TransactionCommand> readAll() {
        try {
            return log.readAll();
        } catch (Exception e) {
            throw new RuntimeException("Error reading file log: " + e.getMessage(), e);
        }
    }

    @Override
    public void clear() {
        log.clear();
    }

    @Override
    public String getDestinationName() {
        return "FILE";
    }
}
