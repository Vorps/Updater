package me.vorps.updater.action;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

/**
 * Project Updater Created by Vorps on 02/09/2016 at 00:26.
 */
public class ActionStop implements Execute{

    @Override
    public boolean run(Plugin plugin){
        Bukkit.broadcastMessage("§cServer shutdown by vorps in 10 sec");
        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            @Override
            public void run() {
                Bukkit.shutdown();
            }
        },10000L);
        return true;
    }

}
