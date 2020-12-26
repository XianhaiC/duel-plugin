package com.commands;

import java.util.ArrayList;
import java.sql.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;

import com.DuelPlugin;

import java.io.*;
import java.util.*;
import java.awt.*;

// TODO figure out how to clone an inventory
// maybe use https://www.spigotmc.org/threads/how-do-i-save-a-players-inventory.38692/?__cf_chl_jschl_tk__=420440af5b43f7dc5398f3442901531e27bc96bd-1608966405-0-AUum4MG6gkD6ioxeVWrpX09Vr-wqsYT6THm6fnoeKsT3uj2GfZbRMGKx_yC0Aki6hyZwvUg0PuoljLkw2ChKQyUvXdYi_NIdJo3hS2Mf4gfOabs5A8XawwFYj8bC8oCgFgEPhLKZIHY_gqr5Uis0m-i6ScSbRvRdxapcrevtsTc2_q_1GmGQm50L4UF-RgPxLhcS1lWFjDTHrOc4CO2wl_2-ZFm5v-o2Idq3f4-ShlzR0VcqW4KreJoVGOGRQBCjKCgt9b_QKw5WjJEKIIwcw_zBvNjYYGO9qIGS3w5PeOK6pNkAhxalPSj61VKH4GYtaV0qkyAY8ydrwgU1Y1_5UuLHRY21DTUTBOFY_AVmgsYsUFPsi6xUCN9KYaA2-f7vuw
//
class ObjectCloner
{
  // so that nobody can accidentally create an ObjectCloner object
  private ObjectCloner(){}
  // returns a deep copy of an object
  static public Object deepCopy(Object oldObj) throws Exception
  {
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;
    try
      {
        ByteArrayOutputStream bos =
          new ByteArrayOutputStream(); // A
        oos = new ObjectOutputStream(bos); // B
        // serialize and pass the object
        oos.writeObject(oldObj);   // C
        oos.flush();               // D
        ByteArrayInputStream bin =
          new ByteArrayInputStream(bos.toByteArray()); // E
        ois = new ObjectInputStream(bin);                  // F
        // return the new object
        return ois.readObject(); // G
      }
    catch(Exception e)
      {
        System.out.println("Exception in ObjectCloner = " + e);
        throw(e);
      }
    finally
      {
        oos.close();
        ois.close();
      }
  }

}

public class SaveCommand implements CommandExecutor {
  private final DuelPlugin plugin;
  private ItemStack[] inv;

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
        inv = (ItemStack[]) ObjectCloner.deepCopy(player.getInventory().getContents());
        player.sendMessage("Inventory saved!");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    else {
      // restore the player's inventory
      player.getInventory().setContents(inv);
      inv = null;
      player.sendMessage("Inventory restored!");
    }

    return true;
  }
}
