package com.ultimismc.skywars.core.placeholders;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.TeamType;
import com.ultimismc.skywars.core.placeholders.stats.*;
import com.ultimismc.skywars.core.user.UserManager;
import xyz.directplan.directlib.placeholder.AbstractPlaceholderExpansion;
import xyz.directplan.directlib.placeholder.PlaceholderProcessorRegistry;

import java.util.Locale;

/**
 * @author DirectPlan
 */
public class SkyWarsPlaceholderExpansion extends AbstractPlaceholderExpansion {

    private final SkyWarsPlugin plugin;
    private final UserManager userManager;

    public SkyWarsPlaceholderExpansion(SkyWarsPlugin plugin) {
        super(plugin, "skywars");

        this.plugin = plugin;
        userManager = plugin.getUserManager();
    }

    @Override
    public void initialize(PlaceholderProcessorRegistry processorRegistry) {

        processorRegistry.register("player-wins", new WinProcessor(userManager));
        processorRegistry.register("player-kills", new KillProcessor(userManager));
        processorRegistry.register("player-winstreak", new WinstreakProcessor(userManager));
        processorRegistry.register("player-bestwinstreak", new BestWinstreakProcessor(userManager));
        processorRegistry.register("player-prog", new CurrentProgressProcessor(userManager));
        processorRegistry.register("player-progress", new CurrentProgressProcessor(userManager));
        processorRegistry.register("player-maxprogress", new RequiredProgressProcessor(userManager));
        processorRegistry.register("player-level", new LevelProcessor(userManager, true));
        processorRegistry.register("player-level-value", new LevelProcessor(userManager, false));
        processorRegistry.register("player-chat-level", new ChatLevelProcessor(userManager));
        processorRegistry.register("player-coins", new CoinProcessor(userManager));
        processorRegistry.register("player-souls", new SoulProcessor(userManager));

        for(TeamType teamType : TeamType.values()) {
            String name = teamType.name().toLowerCase(Locale.ROOT);
            processorRegistry.register(name + "-players", new OnlinePlayersProcessor(userManager, plugin, teamType));
        }
    }
}
