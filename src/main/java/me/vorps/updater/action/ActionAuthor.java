package me.vorps.updater.action;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import java.util.UUID;

/**
 * Project Updater Created by Vorps on 03/09/2016 at 21:26.
 */
public class ActionAuthor implements ExecuteUUID {

    @Override
    public boolean run(final Plugin plugin, final UUID uuid){
        Bukkit.getPluginManager().registerEvents(new Listener() {

            public void onJoinEvent(PlayerJoinEvent event){
                event.getPlayer().sendMessage("Plugin SnoWar By Vorps");
            }

        }, plugin);
        return true;
    }
}
