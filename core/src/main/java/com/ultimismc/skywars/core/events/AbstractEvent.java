package com.ultimismc.skywars.core.events;

import com.ultimismc.skywars.core.game.GameServer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
@Getter
public abstract class AbstractEvent extends Event {

    private static final HandlerList handlerList = new HandlerList();

    private final GameServer gameServer;

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
