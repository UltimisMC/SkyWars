package com.ultimismc.skywars.core;

import com.ultimismc.skywars.core.game.GameConfig;

/**
 * @author DirectPlan
 */
public interface SkyWarsConfigManager {

    /**
     * Loads server configuration from the config file.
     * @return the game info
     */
    GameConfig loadGameConfig(SkyWarsPlugin plugin);

    /**
     * Save the game configuration to the config file.
     * @param plugin the plugin
     */
    void saveGameConfig(SkyWarsPlugin plugin);
}
