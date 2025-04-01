package me.Bamle.lifesteal.database;

import org.bukkit.configuration.file.FileConfiguration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseManager {
    private Connection connection;
    private final FileConfiguration config;

    public DataBaseManager(FileConfiguration config) {
        this.config = config;
    }

    public void connect() {
        String host = config.getString("database.host");
        int port = config.getInt("database.port");
        String database = config.getString("database.database");
        String username = config.getString("database.username");
        String password = config.getString("database.password");

        String url = "jdbc:mysql://" + host + ":" + port + "/" + database; // Use "jdbc:mysql://" for MySQL
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("[Lifesteal] Connected to MariaDB/MySQL database.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("[Lifesteal] Database connection failed!");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("[Lifesteal] Database disconnected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setupDatabase() {
        String query = "CREATE TABLE IF NOT EXISTS lifesteal_data ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "player VARCHAR(36) NOT NULL, "
                + "hearts INT NOT NULL)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.executeUpdate();
            System.out.println("[Lifesteal] Database table ensured.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
