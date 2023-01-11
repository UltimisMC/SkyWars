package com.ultimismc.skywars.game.chest;

import com.ultimismc.skywars.game.events.SkyWarsEventHandler;
import com.ultimismc.skywars.game.events.SkyWarsEventUpdater;
import lombok.RequiredArgsConstructor;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class ChestHologramUpdater implements SkyWarsEventUpdater {

    private final Chest chest;

    @Override
    public void update(SkyWarsEventHandler eventHandler) {
        ChestHologram chestHologram = chest.getChestHologram();
        chestHologram.updateChestHologram(chest, eventHandler);
    }
}
