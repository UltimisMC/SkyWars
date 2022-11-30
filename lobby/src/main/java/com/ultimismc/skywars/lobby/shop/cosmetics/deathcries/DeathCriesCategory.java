package com.ultimismc.skywars.lobby.shop.cosmetics.deathcries;

import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.cosmetics.deathcries.DeathCryHandler;
import com.ultimismc.skywars.lobby.config.ShopMessageKeys;
import com.ultimismc.skywars.lobby.shop.cosmetics.AbstractCosmeticCategory;
import lombok.Getter;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
@Getter
public class DeathCriesCategory extends AbstractCosmeticCategory<DeathCryHandler> {

    private final PurchasableDesign design = new PurchasableDesign(Material.GHAST_TEAR);

    public DeathCriesCategory(DeathCryHandler cosmeticRegistry, int itemSlot) {
        super(cosmeticRegistry, ShopMessageKeys.COSMETIC_DEATH_CRIES_LORE.getStringList(), itemSlot);
    }
}
