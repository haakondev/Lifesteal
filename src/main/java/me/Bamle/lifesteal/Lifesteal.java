package me.Bamle.lifesteal;

import me.Bamle.lifesteal.commands.LifestealReload;
import me.Bamle.lifesteal.dataBase.DataBaseManager;
import me.Bamle.lifesteal.listeners.deathEvent;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class Lifesteal extends JavaPlugin {

    private DataBaseManager databaseManager;

    ArrayList<World> worlds = new ArrayList<>();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        Bukkit.getServer().getPluginManager().registerEvents(new deathEvent(), this);
        getCommand("lifesteal").setExecutor(new LifestealReload(this));
        setWorldBorder();
        databaseManager = new DataBaseManager(getConfig());
        databaseManager.connect();
        databaseManager.setupDatabase();

        Bukkit.getServer().getPluginManager().registerEvents(new deathEvent(), this);
        setWorldBorder();
    }

    @Override
    public void onDisable() {
        if (databaseManager != null) {
            databaseManager.disconnect();
        }
    }



    public void setWorldBorder() {
        FileConfiguration config = getConfig();

        config.getStringList("world-border.worlds").forEach(worldName -> {
            World world = Bukkit.getWorld(worldName);
            if (world != null) {
                worlds.add(world);
            } else {
                getLogger().warning("World " + worldName + " not found. Please check your config.");
            }
        });

        for (World world : worlds) {
            if (world != null) {
                setBorder(world);
            }
        }
    }

    public void setBorder(World world) {
        FileConfiguration config = getConfig();
        WorldBorder border = world.getWorldBorder();

        border.setCenter(config.getDouble("world-border.center-x"), config.getDouble("world-border.center-z"));
        border.setSize(config.getDouble("world-border.size"));
        border.setDamageAmount(config.getDouble("world-border.damage-amount"));
        border.setWarningDistance(config.getInt("world-border.warning-distance"));
    }
}
