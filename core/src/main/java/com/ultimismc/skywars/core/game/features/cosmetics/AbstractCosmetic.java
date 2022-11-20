package com.ultimismc.skywars.core.game.features.cosmetics;

import com.ultimismc.skywars.core.game.currency.Currency;
import com.ultimismc.skywars.core.game.features.PurchasableRarity;
import lombok.Getter;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
@Getter
public abstract class AbstractCosmetic implements Cosmetic {

    private final Currency currency = Currency.COIN_CURRENCY;

    private final String category = "Cosmetic";
    private final Material displayMaterial;
    private final short displayDurability;
    private final String name;
    private final CosmeticRarity cosmeticRarity;
    private final boolean soulWell = false;

    public AbstractCosmetic(Material displayMaterial, int durability, String name, CosmeticRarity rarity) {
        this.displayMaterial = displayMaterial;
        this.displayDurability = (short) durability;
        this.name = name;
        this.cosmeticRarity = rarity;
    }

    public AbstractCosmetic(Material displayMaterial, String name, CosmeticRarity rarity) {
        this(displayMaterial, 0, name, rarity);
    }

    @Override
    public int getPrice() {
        return cosmeticRarity.getPrice();
    }

    @Override
    public PurchasableRarity getRarity() {
        if(cosmeticRarity != null) {
            return cosmeticRarity.getPurchasableRarity();
        }
        return null;
    }
}
