package com.commands;

import java.sql.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.DuelPlugin;

public class DuelCommand implements CommandExecutor {
    private final DuelPlugin plugin;

    public DuelCommand(DuelPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        try {
            Statement stmt = DuelPlugin.connection.createStatement();
            stmt.executeUpdate("INSERT INTO player (name, total_wins, total_duels, win_streak) VALUES ('" + args[0] + "', " + args[1] + ", " + args[2] + ", " + args[3] + ");");
            sender.sendMessage("Inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // do whatever
        return true;
    }
}
