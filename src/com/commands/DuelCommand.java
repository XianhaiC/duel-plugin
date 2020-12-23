package com.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.DuelPlugin;

public class DuelCommand implements CommandExecutor {
    private final JavaPlugin plugin;

    public DuelCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // do whatever
        return true;
    }
}
