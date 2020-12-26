package com;

import java.sql.*;
import java.io.File;

public class DBConnection {
  private DuelPlugin plugin;
  private Connection connection;

  public DBConnection(DuelPlugin plugin) {
    this.plugin = plugin;
  }

  public void openConnection() throws SQLException,
                                      ClassNotFoundException {
    if (connection != null && !connection.isClosed()) {
      return;
    }

    // get the db file path
    File dataFolder = plugin.getDataFolder();
    if(!dataFolder.exists()) {
      dataFolder.mkdir();
    }
    String dbPath = dataFolder.getAbsolutePath() + "/duel.db";

    // may throw ClassNotFoundException
    Class.forName("org.sqlite.JDBC");
    connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
  }

  public void closeConnection() throws SQLException {
    if (connection != null)
      connection.close();
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

  public Connection getConnection() {
    return connection;
  }
}
