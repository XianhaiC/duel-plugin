package com.commands;

import java.util.ArrayList;
import java.sql.*;
import java.io.IOException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Inventory;
import org.bukkit.entity.Player;

import com.DuelPlugin;
import com.util.InventoryToBase64;

public class SaveCommand implements CommandExecutor {
  private final DuelPlugin plugin;
  private String inv;

  public SaveCommand(DuelPlugin plugin) {
    this.plugin = plugin;
    inv = null;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (!(sender instanceof Player))
      return false;

    Player player = (Player) sender;

    if (inv == null) {
      // save the player's inventory
      try {
        inv = InventoryToBase64.toBase64(player.getInventory());
        player.sendMessage("Inventory saved!");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    else {
      // restore the player's inventory
      try {
        Inventory oldInv = InventoryToBase64.fromBase64(inv);
        player.getInventory().setContents(oldInv.getContents());
        player.sendMessage("Inventory restored!");
      } catch (IOException e) {
        player.sendMessage("Inventory couldn't be restored!");
        e.printStackTrace();
      } finally {
        inv = null;
      }
    }

    return true;
  }
}
