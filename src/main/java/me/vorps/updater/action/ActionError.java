package me.vorps.updater.action;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

/**
 * Project Updater Created by Vorps on 03/09/2016 at 18:58.
 */
public class ActionError implements Execute{

    @Override
    public boolean run(final Plugin plugin){
        Bukkit.shutdown();
        return true;
    }

}
