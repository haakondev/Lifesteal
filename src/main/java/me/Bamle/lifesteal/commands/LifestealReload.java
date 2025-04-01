package me.Bamle.lifesteal.commands;

import me.Bamle.lifesteal.Lifesteal;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LifestealReload implements CommandExecutor {
    private final Lifesteal plugin;

    public LifestealReload(Lifesteal plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("lifesteal.reload") || sender.isOp()) {
                plugin.reloadConfig();
                plugin.setWorldBorder();
                sender.sendMessage("§aLifesteal configuration reloaded!");
            } else {
                sender.sendMessage("§cYou do not have permission to do this.");
            }
            return true;
        }

        sender.sendMessage("§cUsage: /lifesteal reload");
        return true;
    }
}
