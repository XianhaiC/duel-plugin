package com.eventhandlers;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.entity.Player;

import com.DuelPlugin;
import com.Duel;

public final class DuelHandler implements Listener {
  private final DuelPlugin plugin;

  public DuelHandler(DuelPlugin plugin) {
    this.plugin = plugin;
    plugin.getServer().getPluginManager().registerEvents(this, plugin);
  }

  @EventHandler
  public void onDuelerDeath(PlayerDeathEvent event) {
    // first, get the duel that the player is part of
    // determine who the winner and losers are
    // update the database for both accordingly
    // /title for both players signifying that the duel is over

    Player deadPlayer = event.getEntity();
    String deadPlayerName = deadPlayer.getPlayerListName();
    Duel duel = Duel.currentDuels.get(deadPlayerName);

    // ensure the player is in a duel
    if (duel == null) return;

    // ensure the duel is ongoing
    if (!duel.getAccepted()) return;


    // figure out who the winner and loser is
    Player winner, loser;
    if (duel.getChallenger().getPlayerListName().equals(deadPlayerName)) {
      winner = duel.getOpponent();
    }
    else {
      winner = duel.getChallenger();
    }
    loser = deadPlayer;

    winner.sendTitle("You won the duel!", "", 20, 60, 20);
    loser.sendTitle("You lost the duel!", "", 20, 60, 20);

    duel.cleanup();
  }
}
