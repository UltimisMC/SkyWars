package com.ultimismc.skywars.core.game.features.perks;

import com.ultimismc.skywars.core.game.currency.Currency;
import lombok.Getter;
import org.bukkit.Material;


/**
 * @author DirectPlan
 */
@Getter
public abstract class AbstractPerk implements Perk {

    private final Currency currency = Currency.COIN_CURRENCY;
    private final String category = "Perk";

    private final Material displayMaterial;
    private final short durability;
    private final String name;
    private final PerkRarity rarity;
    private final boolean soulWellPerk;
    private final String description;

    public AbstractPerk(Material displayMaterial, int durability, String name, PerkRarity rarity, boolean soulWellPerk, String description) {
        this.displayMaterial = displayMaterial;
        this.durability = (short) durability;
        this.name = name;
        this.rarity = rarity;
        this.soulWellPerk = soulWellPerk;
        this.description = description;
    }

    public AbstractPerk(Material displayMaterial, int durability, String name, PerkRarity rarity, String description) {
        this(displayMaterial, durability, name, rarity, true, description);
    }

    public AbstractPerk(Material displayMaterial, String name, PerkRarity rarity, boolean soulWellPerk, String description) {
        this(displayMaterial, 0, name, rarity, soulWellPerk, description);
    }

    public AbstractPerk(Material material, String name, PerkRarity rarity, String description) {
        this(material, name, rarity, true, description);
    }

    @Override
    public short getDisplayDurability() {
        return durability;
    }

    @Override
    public int getPrice() {
        return rarity.getPrice();
    }
}
