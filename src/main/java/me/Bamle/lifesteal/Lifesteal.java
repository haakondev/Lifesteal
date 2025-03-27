package me.Bamle.lifesteal;

import me.Bamle.lifesteal.listeners.deathEvent;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.plugin.java.JavaPlugin;

public final class Lifesteal extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(new deathEvent(), this);
        setWorldBorder();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void setWorldBorder() {
        World[] worlds = {Bukkit.getWorld("world"), Bukkit.getWorld("world_nether"), Bukkit.getWorld("world_the_end")}; // Replace with your world name if different
        for (World world : worlds) {
            if (world != null) {
                setBorder(world);
            }
        }
    }
    public void setBorder(World world) {

        WorldBorder border = world.getWorldBorder();
        border.setCenter(0, 0); // Set border center at (0,0)
        border.setSize(1000); // 1000x1000 area
        border.setDamageAmount(1.0); // Damage per second when outside
        border.setWarningDistance(5); // Warn players when 5 blocks away from border
    }

}
