package com.ultimismc.skywars.core.game.features.cosmetics;

import com.ultimismc.skywars.core.game.currency.Currency;
import com.ultimismc.skywars.core.game.features.Purchasable;
import com.ultimismc.skywars.core.game.features.PurchasableRarity;
import lombok.Getter;

/**
 * @author DirectPlan
 */
@Getter
public abstract class Cosmetic implements Purchasable {

    private final Currency currency = Currency.COIN_CURRENCY;

    private final String category = "Cosmetic";
    private final String name;
    private final CosmeticRarity cosmeticRarity;
    private final boolean soulWell = false;

    public Cosmetic(String name, CosmeticRarity rarity) {
        this.name = name;
        this.cosmeticRarity = rarity;
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
