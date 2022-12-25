package com.ultimismc.skywars.core;

import com.ultimismc.skywars.core.game.GameConfig;

/**
 * @author DirectPlan
 */
public interface ServerInitializer {

    /**
     * Loads server configuration from the config file.
     * @return the game info
     */
    GameConfig loadGameConfig(SkyWarsPlugin plugin);

    /**
     * Save the game configuration to the config file.
     * @param plugin the skywars plugin
     */
    void saveGameConfig(SkyWarsPlugin plugin);
}
