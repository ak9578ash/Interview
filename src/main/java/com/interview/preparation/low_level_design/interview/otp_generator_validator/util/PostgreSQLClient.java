package com.interview.preparation.low_level_design.interview.otp_generator_validator.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLClient {
    private static final String url = "jdbc:postgresql://localhost:5432/test_db";
    private static final String user = "postgres";
    private static final String password = "postgres";
    private final Connection connection;

    public PostgreSQLClient() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        }catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the PostgreSQL database.", e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to close the PostgreSQL connection.", e);
        }
    }
}
