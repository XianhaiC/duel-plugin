package com.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.DuelPlugin;

public class DuelCommand implements CommandExecutor {
  private final DuelPlugin plugin;

  public DuelCommand(DuelPlugin plugin) {
    this.plugin = plugin;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    // 1. get the player who issued the command, who we will call the
    //    'challenger', and the target opponent, who we will call the 'opponent',
    //    specified.
    // 2. create a duel object with both the challenger and opponent, as well
    //    as the settings of the duel (save location, save inventory, etc)
    // 3. in order to track who has issued a duel request with who, you'll
    //    need to create a hashmap that maps the opponent name to the duel object
    //    and the challenger name to the duel object as well. We can then
    //    retrieve the duel object using the name of the opponent
    // 4. send a message to the opponent, prompting them to accept the duel

    // TODO: delete this
    sender.sendMessage("Command not implemented");
    return true;
  }
}
