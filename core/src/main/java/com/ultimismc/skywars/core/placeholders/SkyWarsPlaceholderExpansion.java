package com.ultimismc.skywars.core.placeholders;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.TeamType;
import com.ultimismc.skywars.core.placeholders.stats.*;

import java.util.Locale;

/**
 * @author DirectPlan
 */
public class SkyWarsPlaceholderExpansion extends UserPlaceholderExpansion {

    public SkyWarsPlaceholderExpansion(SkyWarsPlugin plugin) {
        super(plugin, "skywars");

        processorHandler.registerProcessor("player-wins", new WinProcessor());
        processorHandler.registerProcessor("player-kills", new KillProcessor());
        processorHandler.registerProcessor("player-prog", new CurrentProgressProcessor());
        processorHandler.registerProcessor("player-progress", new CurrentProgressProcessor());
        processorHandler.registerProcessor("player-maxprogress", new RequiredProgressProcessor());
        processorHandler.registerProcessor("player-level", new LevelProcessor());
        processorHandler.registerProcessor("player-level-value", new LevelProcessor(true));
        processorHandler.registerProcessor("player-chat-level", new ChatLevelProcessor());
        processorHandler.registerProcessor("player-coins", new CoinProcessor());
        processorHandler.registerProcessor("player-souls", new SoulProcessor());

        for(TeamType teamType : TeamType.values()) {
            String name = teamType.name().toLowerCase(Locale.ROOT);
            processorHandler.registerProcessor(name + "-players", new OnlinePlayersProcessor(plugin, teamType));
        }
    }
}
