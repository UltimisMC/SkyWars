package com.ultimismc.skywars.lobby.shop.cosmetics.victorydances;

import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.cosmetics.victorydances.VictoryDanceHandler;
import com.ultimismc.skywars.lobby.config.ShopMessageKeys;
import com.ultimismc.skywars.lobby.shop.cosmetics.AbstractCosmeticCategory;
import lombok.Getter;

/**
 * @author DirectPlan
 */
@Getter
public class VictoryDancesCategory extends AbstractCosmeticCategory<VictoryDanceHandler> {
    
    private final PurchasableDesign design = new PurchasableDesign("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDczYWY2OWVkOWJmNjdlMmY1NDAzZGQ3ZDI4YmJlMzIwMzQ3NDliYmZiNjM1YWMxNzg5YTQxMjA1M2NkY2JmMCJ9fX0=");

    public VictoryDancesCategory(VictoryDanceHandler cosmeticRegistry) {
        super(cosmeticRegistry, ShopMessageKeys.COSMETIC_VICTORY_DANCES_LORE.getStringList(), 14);
    }
    // TODO: ADD AN ABSTRACT CLASS SO THAT YOU COULD RECTIFY THE STATS LIKE UNLOCKED/TOTAL AND SELECTED
    // Funny
}
