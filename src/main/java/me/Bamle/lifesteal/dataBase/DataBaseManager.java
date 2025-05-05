package me.Bamle.lifesteal.dataBase;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.bukkit.configuration.file.FileConfiguration;
import org.mariadb.jdbc.MariaDbPoolDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class DataBaseManager {

    MysqlDataSource dataSource;
    Connection connection;
    FileConfiguration config;

    public DataBaseManager(FileConfiguration config) {
        this.config = config;
    }


    public boolean connect() {
        try {
            String url = "jdbc:mysql://" + "localhost" + ":" + "3306" + "/" + "lifesteal";
            dataSource = new MysqlDataSource(url);
            dataSource.setUser("root");
            dataSource.setPassword("");
            connection = dataSource.getConnection();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public abstract boolean setupDatabase();
    public boolean disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Connection getConnection() {
        return connection;
    }

    public MariaDbPoolDataSource getDataSource() {
        return dataSource;
    }
}
