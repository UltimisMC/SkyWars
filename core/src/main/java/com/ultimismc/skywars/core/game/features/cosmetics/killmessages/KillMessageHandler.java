package com.ultimismc.skywars.core.game.features.cosmetics.killmessages;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.features.PurchasableFeature;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DirectPlan
 */
@Getter
public class KillMessageHandler implements PurchasableFeature<KillMessage> {

    private final String name = "Kill Messages";

    private final Map<String, KillMessage> killMessages = new HashMap<>();

    @Override
    public void initializeFeature(SkyWarsPlugin plugin) {

    }

    @Override
    public Map<String, KillMessage> getPurchasables() {
        return killMessages;
    }
}
