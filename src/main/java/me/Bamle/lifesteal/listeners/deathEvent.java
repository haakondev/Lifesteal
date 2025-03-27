package me.Bamle.lifesteal.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.UUID;

public class deathEvent implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        Player killer = e.getEntity().getKiller();
        if (killer == null) {
            return;
        }

        p.spigot().respawn();

        p.setMaxHealth(p.getMaxHealth() - 1);
        p.setHealth(p.getMaxHealth());
        killer.setMaxHealth(killer.getMaxHealth() + 1);
        killer.sendMessage("You have stolen a heart!");
        p.sendMessage("You have lost a heart!");

        if (p.getMaxHealth() <= 0) {
            p.setGameMode(GameMode.SPECTATOR);
        }
    }

}
