package com.ultimismc.skywars.core.server;

import com.ultimismc.gamescaler.ServerPlugin;
import com.ultimismc.skywars.core.SkyWarsPlugin;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import xyz.directplan.directlib.PluginUtility;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class SkyWarsPluginWrapper implements ServerPlugin {

    private final SkyWarsPlugin plugin;

    @Override
    public String getName() {
        return plugin.getName();
    }

    @Override
    public boolean isWhitelisted() {
        return plugin.getServer().hasWhitelist();
    }

    @Override
    public int getOnlinePlayers() {
        return Bukkit.getOnlinePlayers().size();
    }

    @Override
    public int getMaximumPlayers() {
        return Bukkit.getMaxPlayers();
    }

    @Override
    public void broadcastMessage(String message) {
        PluginUtility.broadcastMessage(message);
    }

    @Override
    public void log(String message) {
        plugin.log(message);
    }

    @Override
    public void shutdown(String reason) {
        plugin.shutdown(reason);
    }
}
