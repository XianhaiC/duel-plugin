package com;

import org.bukkit.plugin.java.JavaPlugin;
import com.pablo67340.SQLiteLib.Main.SQLiteLib;

import com.commands.LeaderboardCommand;

public class DuelPlugin extends JavaPlugin {
  public SQLiteLib sqlLib;

  @Override
  public void onEnable() {
    sqlLib = SQLiteLib.hookSQLiteLib();
    sqlLib.initializeDatabase(pluginInstance, "duel", "CREATE TABLE IF NOT EXISTS table_name");

    // new ArrowHandler(this);
    // new DamageHandler(this);
    //this.getCommand("duelboards").setExecutor(new LeaderboardCommand(this));
  }
}
