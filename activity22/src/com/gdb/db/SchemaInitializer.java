package com.gdb.db;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SchemaInitializer {

    public static void initialize(ConnectionProvider provider) {
        String sql = readSchemaSql();
        if (sql == null || sql.trim().isEmpty()) {
            return;
        }
        try (Connection conn = provider.getConnection();
             Statement stmt = conn.createStatement()) {
            String[] statements = sql.split(";");
            for (String statement : statements) {
                String trimmed = statement.trim();
                if (!trimmed.isEmpty()) {
                    stmt.execute(trimmed);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize database schema: " + e.getMessage(), e);
        }
    }

    private static String readSchemaSql() {
        try (InputStream is = SchemaInitializer.class.getResourceAsStream("/schema.sql")) {
            if (is != null) {
                return new String(is.readAllBytes(), StandardCharsets.UTF_8);
            }
        } catch (Exception e) {
            // Ignored, try next
        }
        try (InputStream is = SchemaInitializer.class.getClassLoader().getResourceAsStream("schema.sql")) {
            if (is != null) {
                return new String(is.readAllBytes(), StandardCharsets.UTF_8);
            }
        } catch (Exception e) {
            // Ignored, try next
        }
        try {
            return new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get("src/main/resources/schema.sql")), StandardCharsets.UTF_8);
        } catch (Exception e) {
            // Ignored, fallback
        }
        return "CREATE TABLE IF NOT EXISTS accounts (" +
                "account_number INTEGER PRIMARY KEY," +
                "account_holder_name TEXT NOT NULL," +
                "age INTEGER NOT NULL," +
                "balance REAL NOT NULL," +
                "account_type TEXT NOT NULL," +
                "status TEXT NOT NULL," +
                "pin INTEGER," +
                "opening_date TEXT" +
                ");" +
                "CREATE TABLE IF NOT EXISTS transactions (" +
                "id TEXT PRIMARY KEY," +
                "timestamp TEXT NOT NULL," +
                "account_number INTEGER NOT NULL," +
                "type TEXT NOT NULL," +
                "amount REAL NOT NULL," +
                "balance_after REAL NOT NULL," +
                "status TEXT NOT NULL," +
                "remarks TEXT," +
                "from_account INTEGER," +
                "to_account INTEGER" +
                ");";
    }
}
