package com.ultimismc.skywars.core.server;

import com.ultimismc.serversync.ServerPlugin;
import com.ultimismc.skywars.core.SkyWarsPlugin;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import xyz.directplan.directlib.PluginUtility;

import java.util.logging.Level;

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
    public void log(Level level, String s) {
        plugin.getLogger().log(level, s);
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
        plugin.shutdownServer(reason);
    }
}
