package com.ultimismc.skywars.core.game.features.cosmetics.victorydances.impl;

import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.cosmetics.CosmeticRarity;
import com.ultimismc.skywars.core.game.features.cosmetics.victorydances.VictoryDance;
import com.ultimismc.skywars.core.game.features.cosmetics.victorydances.VictoryDanceExecutor;
import lombok.Getter;
import org.bukkit.Material;
import xyz.directplan.directlib.PluginUtility;

/**
 * @author DirectPlan
 */
@Getter
public class FireworksVictoryDance extends VictoryDance {

    private final PurchasableDesign design = new PurchasableDesign(Material.FIREWORK);

    public FireworksVictoryDance() {
        super("Fireworks", CosmeticRarity.COMMON);

        addDescription("Celebrate with a splendid");
        addDescription("fireworks show!");
    }

    @Override
    public boolean isDefault() {
        return true;
    }

    @Override
    public VictoryDanceExecutor executor() {
        return (user, tick) -> PluginUtility.spawnCoolFirework(user.getPlayer());
    }
}
