package com.ultimismc.skywars.core.game.features.cosmetics;

import com.ultimismc.skywars.core.game.currency.Currency;
import com.ultimismc.skywars.core.game.features.Purchasable;
import com.ultimismc.skywars.core.game.features.PurchasableRarity;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

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

    private final List<String> description = new ArrayList<>();

    /*
        Projectile Trails - Needs implementation
        Cages - Need to be added (tomorrow)
        Victory Dances - Needs implementation
        Kill Effects - Needs implementation
        Death Cries - Completed
        Ballons - Needs design & implementation
        Kill Messages - Completed
        Sprays - Needs design & implementation
     */
    public Cosmetic(String name, CosmeticRarity rarity) {
        this.name = name;
        this.cosmeticRarity = rarity;
    }

    protected void addDescription(String description) {
        this.description.add(description);
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
