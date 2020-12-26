package com.eventhandlers;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.ProjectileHitEvent;

import com.DuelPlugin;

public final class DuelHandler implements Listener {
  private final DuelPlugin plugin;

  public DuelHandler(DuelPlugin plugin) {
    this.plugin = plugin;
    plugin.getServer().getPluginManager().registerEvents(this, plugin);
  }

  @EventHandler
  public void onDuelerDeath(ProjectileHitEvent event) {
    // first, get the duel that the player is part of
    // determine who the winner and losers are
    // update the database for both accordingly
    // /title for both players signifying that the duel is over

    plugin.getLogger().info("EVENT TRIGGERED");
  }

  public void enableListeners() {
    plugin.getServer().getPluginManager().registerEvents(this, plugin);
  }

  public void disableListeners() {
    HandlerList.unregisterAll(this);
  }
}
