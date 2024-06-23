package com.ultimismc.skywars.core.placeholders;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.TeamType;
import com.ultimismc.skywars.core.placeholders.stats.*;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserManager;
import org.bukkit.entity.Player;

import java.util.Locale;

/**
 * @author DirectPlan
 */
public class SkyWarsPlaceholderExpansion extends UserPlaceholderFactory {

    private final UserManager userManager;

    public SkyWarsPlaceholderExpansion(SkyWarsPlugin plugin) {
        super(plugin, "skywars");

        userManager = plugin.getUserManager();

        registerProcessor("player-wins", new WinProcessor());
        registerProcessor("player-kills", new KillProcessor());
        registerProcessor("player-winstreak", new WinstreakProcessor());
        registerProcessor("player-bestwinstreak", new BestWinstreakProcessor());
        registerProcessor("player-prog", new CurrentProgressProcessor());
        registerProcessor("player-progress", new CurrentProgressProcessor());
        registerProcessor("player-maxprogress", new RequiredProgressProcessor());
        registerProcessor("player-level", new LevelProcessor(true));
        registerProcessor("player-level-value", new LevelProcessor(false));
        registerProcessor("player-chat-level", new ChatLevelProcessor());
        registerProcessor("player-coins", new CoinProcessor());
        registerProcessor("player-souls", new SoulProcessor());

        for(TeamType teamType : TeamType.values()) {
            String name = teamType.name().toLowerCase(Locale.ROOT);
            registerProcessor(name + "-players", new OnlinePlayersProcessor(plugin, teamType));
        }
    }

    @Override
    public User getPlayer(Player player) {
        return userManager.getCachedUser(player);
    }
}
