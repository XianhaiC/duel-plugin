package com;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Location;

import com.DuelSettings;

public class Duel {
  private Player challenger;
  private Player opposer;
  private boolean accepted = false;

  private ItemStack[] challengerInvSaved;
  private ItemStack[] opposerInvSaved;
  private Location challengerLocSaved;
  private Location opposerLocSaved;

  public Duel(Player challenger, Player opposer, DuelSettings settings) {

  }
}
