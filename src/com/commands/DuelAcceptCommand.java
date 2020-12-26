package com.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.DuelPlugin;

public class DuelAcceptCommand implements CommandExecutor {
  private final DuelPlugin plugin;

  public DuelAcceptCommand(DuelPlugin plugin) {
    this.plugin = plugin;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    // 1. get the player who accepts the challenge (opponent)
    // 2. use the hashmap to get the duel object associated with the opponent
    // 3. grab the challenger from the duel object
    // 4. set the duel object's accepted flag to be true
    // 5. enable the duel end handlers
    // 6. send a message to both challenger and opponent saying that the
    //    duel has begun

    // TODO: delete this
    sender.sendMessage("Command not implemented");
    return true;
  }
}
