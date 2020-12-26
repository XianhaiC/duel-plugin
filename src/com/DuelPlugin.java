package com;

import java.sql.*;
import org.bukkit.plugin.java.JavaPlugin;

import com.DBConnection;
import com.commands.AddDBCommand;
import com.commands.LeaderboardCommand;
import com.commands.DuelCommand;
import com.commands.DuelAcceptCommand;
import com.commands.SaveCommand;
import com.eventhandlers.DuelHandler;

public class DuelPlugin extends JavaPlugin {
  private static DBConnection connection;
  private static DuelHandler duelHandler;

  @Override
  public void onEnable() {
    // create a connection to the db
    connection = new DBConnection(this);

    try {
      // open the connection and create the db if it doesn't exist
      connection.openConnection();
      connection.createDatabase();

      getLogger().info("Successfully initialized database connection.");
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    // attach our command executors
    this.getCommand("leaderboard").setExecutor(new LeaderboardCommand(this));
    this.getCommand("adddb").setExecutor(new AddDBCommand(this));
    this.getCommand("saveinv").setExecutor(new SaveCommand(this));
    this.getCommand("duel").setExecutor(new DuelCommand(this));
    this.getCommand("duelaccept").setExecutor(new DuelAcceptCommand(this));

    // attach our event listeners
    duelHandler = new DuelHandler(this);
  }

  @Override
  public void onDisable() {
    try {
      // open the connection and create the db if it doesn't exist
      connection.closeConnection();
      getLogger().info("Successfully closed database connection.");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static Connection getDB() {
    return connection.getConnection();
  }

  public static DuelHandler getDuelHandler() {
    return duelHandler;
  }
}
