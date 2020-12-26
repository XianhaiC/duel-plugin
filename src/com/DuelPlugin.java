package com;

import java.sql.*;
import java.io.File;
import org.bukkit.plugin.java.JavaPlugin;

import com.commands.LeaderboardCommand;
import com.commands.DuelCommand;

public class DuelPlugin extends JavaPlugin {
  public static Connection connection;

  @Override
  public void onEnable() {
    try {
      openConnection();
      createDatabase();
      getLogger().info("Successfully created player db!");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    this.getCommand("leaderboard").setExecutor(new LeaderboardCommand(this));
    this.getCommand("duel").setExecutor(new DuelCommand(this));
  }

  public void openConnection() throws SQLException,
                                      ClassNotFoundException {
    if (connection != null && !connection.isClosed()) {
      return;
    }

    // get the db file path
    File dataFolder = getDataFolder();
    if(!dataFolder.exists()) {
      dataFolder.mkdir();
    }
    String dbPath = dataFolder.getAbsolutePath() + "/duel.db";

    Class.forName("org.sqlite.JDBC");
    connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
  }

  public void createDatabase() throws SQLException {
    Statement cmd = connection.createStatement();
    String cmdContent =
      "CREATE TABLE IF NOT EXISTS player " +
      "(name TEXT NOT NULL, " +
      " total_wins INT NOT NULL, " +
      " total_duels INT NOT NULL, " +
      " win_streak INT NOT NULL)";
    cmd.executeUpdate(cmdContent);
    cmd.close();
  }
}
