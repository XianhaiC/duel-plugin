package com;

import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.Location;

import com.DuelSettings;
import com.util.InventoryToBase64;

public class Duel {
  public static Map<String, Duel> currentDuels = new HashMap<String, Duel>();

  private Player challenger;
  private Player opponent;
  private DuelSettings settings;
  private boolean accepted = false;

  private String challengerInvSaved;
  private String opponentInvSaved;
  private Location challengerLocSaved;
  private Location opponentLocSaved;

  public Duel(Player challenger, Player opponent, DuelSettings settings) {
    this.challenger = challenger;
    this.opponent = opponent;
    this.settings = settings;

    // store player data based on settings
    if (settings.saveInv) {
      challengerInvSaved = InventoryToBase64.toBase64(challenger.getInventory());
      opponentInvSaved = InventoryToBase64.toBase64(opponent.getInventory());
    }

    if (settings.saveLoc) {
      challengerLocSaved = challenger.getLocation();
      opponentLocSaved = opponent.getLocation();
    }
  }

  public Player getChallenger() { return challenger; }
  public Player getOpponent() { return opponent; }
  public DuelSettings getSettings() { return settings; }
  public boolean getAccepted() { return accepted; }
  public void setAccepted(boolean accepted) {
    this.accepted = accepted;
  }

  // cleanup after the duel, restoring player data
  public void cleanup() {
    if (settings.saveInv) {
      try {
        Inventory oldChallengerInv = InventoryToBase64.fromBase64(challengerInvSaved);
        Inventory oldOpponentInv = InventoryToBase64.fromBase64(opponentInvSaved);
        challenger.getInventory().setContents(oldChallengerInv.getContents());
        opponent.getInventory().setContents(oldOpponentInv.getContents());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    if (settings.saveLoc) {
      challenger.teleport(challengerLocSaved);
      opponent.teleport(opponentLocSaved);
    }

    Duel.currentDuels.remove(challenger.getPlayerListName());
    Duel.currentDuels.remove(opponent.getPlayerListName());
  }
}
