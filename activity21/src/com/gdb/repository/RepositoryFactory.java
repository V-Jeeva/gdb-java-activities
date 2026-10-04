package com.gdb.repository;

import java.io.InputStream;
import java.util.Properties;

public class RepositoryFactory {

    private static AccountRepository accountRepositoryInstance;
    private static TransactionRepository transactionRepositoryInstance;

    public static String getPersistenceMode() {
        try (InputStream is = RepositoryFactory.class.getClassLoader().getResourceAsStream("config/persistence.properties")) {
            if (is != null) {
                Properties props = new Properties();
                props.load(is);
                String mode = props.getProperty("persistence.mode");
                if (mode != null && !mode.trim().isEmpty()) {
                    return mode.trim();
                }
            }
        } catch (Exception e) {
            // ignore and fallback
        }
        return "memory";
    }

    public static synchronized AccountRepository getAccountRepository() {
        if (accountRepositoryInstance == null) {
            String mode = getPersistenceMode();
            if ("memory".equalsIgnoreCase(mode)) {
                accountRepositoryInstance = new InMemoryAccountRepository();
            } else if ("jdbc".equalsIgnoreCase(mode)) {
                throw new UnsupportedOperationException("JDBC repository not implemented yet - coming in Activity 22");
            } else if ("file".equalsIgnoreCase(mode)) {
                throw new UnsupportedOperationException("File repository not implemented yet");
            } else {
                accountRepositoryInstance = new InMemoryAccountRepository();
            }
        }
        return accountRepositoryInstance;
    }

    public static synchronized TransactionRepository getTransactionRepository() {
        if (transactionRepositoryInstance == null) {
            String mode = getPersistenceMode();
            if ("memory".equalsIgnoreCase(mode)) {
                transactionRepositoryInstance = new InMemoryTransactionRepository();
            } else if ("jdbc".equalsIgnoreCase(mode)) {
                throw new UnsupportedOperationException("JDBC repository not implemented yet - coming in Activity 22");
            } else if ("file".equalsIgnoreCase(mode)) {
                throw new UnsupportedOperationException("File repository not implemented yet");
            } else {
                transactionRepositoryInstance = new InMemoryTransactionRepository();
            }
        }
        return transactionRepositoryInstance;
    }
}
