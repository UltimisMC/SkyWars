package com.ultimismc.skywars.game.chest;

import com.ultimismc.skywars.game.events.SkyWarsEvent;
import com.ultimismc.skywars.game.events.SkyWarsEventHandler;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import xyz.directplan.directlib.misc.hologram.Hologram;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class ChestHologram {

    private Hologram hologram;

    public void updateChestHologram(Chest chest, SkyWarsEventHandler eventHandler) {
        SkyWarsEvent nextEvent = eventHandler.getNextEvent();
        if(!(nextEvent instanceof ChestRefillSkyWarsEvent)) return;
        if(!chest.isOpened()) return;
        if(hologram == null) {
            hologram = new Hologram(chest.getLocation().add(0.5, 0.5, 0.5));
        }
        String timeLine = ChatColor.GREEN + eventHandler.getTimeLeftForNextEvent();
        if(chest.isEmpty()) {
            hologram.updateLines(timeLine, "&cEmpty!");
            return;
        }
        hologram.updateLines(timeLine);
    }

    public void destroy() {
        if(hologram == null) return;
        hologram.destroy();
    }
}
