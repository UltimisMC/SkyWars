package com.ultimismc.skywars.core.game.features.cosmetics.victorydances;

import com.ultimismc.skywars.core.game.features.cosmetics.Cosmetic;
import com.ultimismc.skywars.core.game.features.cosmetics.CosmeticRarity;
import lombok.Getter;

/**
 * @author DirectPlan
 */
@Getter
public abstract class VictoryDance extends Cosmetic {

    private final String category = "Victory Dance";
    private final int intervalTicks;

    public VictoryDance(String name, CosmeticRarity rarity, int intervalTicks) {
        super(name, rarity);
        this.intervalTicks = intervalTicks;
    }

    public VictoryDance(String name, CosmeticRarity rarity) {
        this(name, rarity, 20);
    }

    public abstract VictoryDanceExecutor executor();
}
