package com.ultimismc.skywars.core.events;

import com.ultimismc.skywars.core.game.GameConfig;
import com.ultimismc.skywars.core.game.GameType;
import com.ultimismc.skywars.core.game.TeamType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
@Getter
public abstract class SkyWarsEvent extends Event {

    private static final HandlerList handlerList = new HandlerList();

    private final GameConfig gameConfig;

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    public GameType getGameType() {
        return gameConfig.getGameType();
    }

    public TeamType getTeamType() {
        return gameConfig.getTeamType();
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
