package com.eventhandlers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.DuelPlugin;

public final class DuelEndHandler implements Listener {

    public DuelEndHandler(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    // @EventHandler
    // public void onDuelerDeath(ProjectileHitEvent event) {
        // // do whatever
    // }
}
