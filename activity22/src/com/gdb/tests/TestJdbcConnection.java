package com.gdb.tests;

import com.gdb.db.ConnectionProvider;
import com.gdb.db.JdbcConnectionProvider;
import com.gdb.db.SchemaInitializer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestJdbcConnection {
    public static void main(String[] args) throws Exception {
        System.out.println("=".repeat(60));
        System.out.println("  ACTIVITY 22 — JDBC FOUNDATION (CONNECTION & SCHEMA)");
        System.out.println("=".repeat(60));

        String testDbUrl = "jdbc:sqlite:test_gdb.db";
        ConnectionProvider provider = new JdbcConnectionProvider(testDbUrl);

        System.out.println("[TEST 1] Database Connection Establishment:");
        try (Connection conn = provider.getConnection()) {
            System.out.println("  Connected to: " + testDbUrl);
            System.out.println("  Driver Name: " + conn.getMetaData().getDriverName());
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("SELECT 1");
            }
            System.out.println("  -> PASSED");
        }

        System.out.println("\n[TEST 2] Schema DDL Execution:");
        System.out.println("  Creating tables: accounts, transactions...");
        SchemaInitializer.initialize(provider);
        System.out.println("  Tables initialized successfully.");
        System.out.println("  -> PASSED");

        System.out.println("\n[TEST 3] Schema Verification:");
        boolean accountsExists = false;
        boolean transactionsExists = false;
        try (Connection conn = provider.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table'")) {
            while (rs.next()) {
                String tableName = rs.getString("name");
                if ("accounts".equalsIgnoreCase(tableName)) {
                    accountsExists = true;
                } else if ("transactions".equalsIgnoreCase(tableName)) {
                    transactionsExists = true;
                }
            }
        }
        System.out.println("  Table 'accounts' exists: " + accountsExists);
        System.out.println("  Table 'transactions' exists: " + transactionsExists);
        assert accountsExists : "Table 'accounts' does not exist in SQLite database!";
        assert transactionsExists : "Table 'transactions' does not exist in SQLite database!";
        System.out.println("  -> PASSED");
        
        provider.shutdown();
        System.out.println();
        System.out.println("=".repeat(60));
        System.out.println("  ALL ACTIVITY 22 JDBC CONNECTION TESTS PASSED!");
        System.out.println("=".repeat(60));
    }
}
