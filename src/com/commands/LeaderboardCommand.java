package com.commands;

import java.sql.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.DuelPlugin;

public class LeaderboardCommand implements CommandExecutor {
    private final DuelPlugin plugin;

    public LeaderboardCommand(DuelPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // do whatever
        try {
            Statement stmt = DuelPlugin.connection.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM player;");
            sender.sendMessage("LEADERBOARD:");
            while (result.next()) {
                sender.sendMessage(result.getString("name") + " " + result.getString("total_wins") + " " + result.getString("total_duels") + " " + result.getString("win_streak"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return true;
    }
}
