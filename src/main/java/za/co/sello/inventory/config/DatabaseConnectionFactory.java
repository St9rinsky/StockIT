package za.co.sello.inventory.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionFactory {

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    DatabaseConfig.URL,
                    DatabaseConfig.USER,
                    DatabaseConfig.PASSWORD
            );
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to database", e);
        }
    }
}