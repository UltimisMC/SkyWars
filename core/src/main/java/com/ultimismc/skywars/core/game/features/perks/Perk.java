package com.ultimismc.skywars.core.game.features.perks;

import com.ultimismc.skywars.core.game.currency.Currency;
import com.ultimismc.skywars.core.game.features.Purchasable;
import com.ultimismc.skywars.core.game.features.PurchasableRarity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @author DirectPlan
 */
@Getter
public abstract class Perk implements Purchasable {

    private final Currency currency = Currency.COIN_CURRENCY;
    private final String category = "Perk";

    private final String name;
    private final PerkRarity perkRarity;
    private final boolean soulWell;
    private final List<String> description;

    @Setter private int price;

    public Perk(String name, PerkRarity rarity, boolean soulWellPerk, List<String> description) {
        this.name = name;
        this.perkRarity = rarity;
        this.soulWell = soulWellPerk;
        this.description = description;
    }

    public Perk(String name, PerkRarity rarity, boolean soulWellPerk, String description) {
        this(name, rarity, soulWellPerk, new ArrayList<>(Collections.singletonList(description)));
    }

    public Perk(String name, PerkRarity rarity, List<String> description) {
        this(name, rarity, true, description);
    }

    public Perk(String name, PerkRarity rarity, String description) {
        this(name, rarity, true, description);
    }

    @Override
    public int getPrice() {
        if(perkRarity == PerkRarity.SOUL_WELL) return price;
        return perkRarity.getPrice();
    }

    @Override
    public PurchasableRarity getRarity() {
        if(perkRarity != null) {
            return perkRarity.getPurchasableRarity();
        }
        return null;
    }

    public boolean isSoulWellPerk() {
        return perkRarity == null || perkRarity == PerkRarity.SOUL_WELL;
    }
}
