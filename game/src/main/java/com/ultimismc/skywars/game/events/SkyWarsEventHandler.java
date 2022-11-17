package com.ultimismc.skywars.game.events;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.game.handler.GameHandler;
import lombok.Getter;
import org.bukkit.scheduler.BukkitTask;
import xyz.directplan.directlib.DateUtil;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author DirectPlan
 */
public class SkyWarsEventHandler {

    private final SkyWarsPlugin plugin;
    private final GameHandler gameHandler;

    private final Iterator<SkyWarsEvent> eventIterator;
    @Getter private SkyWarsEvent nextEvent;
    private long eventStartedAt;
    private BukkitTask task;

    public SkyWarsEventHandler(SkyWarsPlugin plugin, GameHandler gameHandler) {
        this.plugin = plugin;
        this.gameHandler = gameHandler;

        LinkedList<SkyWarsEvent> skyWarsEvents = new LinkedList<>();
        skyWarsEvents.addLast(new RefillSkyWarsEvent(12000));


        eventIterator = skyWarsEvents.iterator();
    }

    public void startNextEvent() {
        if(task == null) {
            task = plugin.getServer().getScheduler().runTaskTimer(plugin, new SkyWarsEventRunnable(this), 20L, 20L);
        }
        eventStartedAt = System.currentTimeMillis();
        nextEvent = eventIterator.next();
    }

    public void executeNextEvent() {
        if(nextEvent == null) return;
        nextEvent.executeEvent(gameHandler);
        startNextEvent();
    }

    public long getUntilNextEvent() {
        long timePassed = (System.currentTimeMillis() - eventStartedAt);
        return (nextEvent.getScheduledIn() - timePassed);
    }

    public String getNextEventDisplayFormat() {
        long timeLeft = getUntilNextEvent();
        return (nextEvent.getName() + " " + DateUtil.readableTime(timeLeft));
    }
}
