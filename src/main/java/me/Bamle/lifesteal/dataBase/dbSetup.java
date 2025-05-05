package me.Bamle.lifesteal.dataBase;

import me.Bamle.lifesteal.dataBase.DataBaseManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class dbSetup extends DataBaseManager {


    @Override
    public boolean setupDatabase() {
        try {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS lifesteal (uuid VARCHAR(36) PRIMARY KEY, hearts INT)";
            PreparedStatement preparedStatement = connection.prepareStatement(createTableSQL);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public dbSetup(FileConfiguration config) {
        super(config);
    }

    public void getHearts(Player p) {
        try {
            String getHeartsSQL = "SELECT hearts FROM lifesteal WHERE uuid = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(getHeartsSQL);
            preparedStatement.setString(1, p.getUniqueId().toString());
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setHearts(int hearts, Player p) {
        try {
            String setHeartsSQL = "UPDATE lifesteal SET hearts = ? WHERE uuid = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(setHeartsSQL);
            preparedStatement.setInt(1, hearts);
            preparedStatement.setString(2, p.getUniqueId().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
