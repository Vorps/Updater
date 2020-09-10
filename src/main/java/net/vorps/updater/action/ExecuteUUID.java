package net.vorps.updater.action;

import org.bukkit.plugin.Plugin;

import java.util.UUID;

/**
 * Project Updater Created by Vorps on 02/09/2016 at 01:19.
 */
public interface ExecuteUUID{

    boolean run(Plugin plugin, UUID uuid);
}
