package me.vorps.updater.action;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

/**
 * Project Updater Created by Vorps on 02/09/2016 at 00:29.
 */
public class ActionWarning implements Execute {

    @Override
    public boolean run(Plugin plugin){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage("§cPlugin SnoWar By Vorps violation of law, Please contact author");
            }
        },0, 1000L);
        return true;
    }
}
