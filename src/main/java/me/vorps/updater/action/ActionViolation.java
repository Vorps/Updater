package me.vorps.updater.action;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

/**
 * Project Updater Created by Vorps on 02/09/2016 at 00:33.
 */
public class ActionViolation implements Execute {

    @Override
    public boolean run(Plugin plugin){
        Bukkit.broadcastMessage("§cViolation of law Plugin SnoWar");
        Bukkit.getPluginManager().disablePlugin(plugin);
        return plugin.getDataFolder().delete();
    }
}
