package com.ultimismc.skywars.core.game.features.perks;

import com.ultimismc.skywars.core.game.currency.Currency;
import com.ultimismc.skywars.core.game.features.PurchasableRarity;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


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
    private final PerkRarity perkRarity;
    private final boolean soulWell;
    private final List<String> description;

    @Setter private int price;

    public AbstractPerk(Material displayMaterial, int durability, String name, PerkRarity rarity, boolean soulWellPerk, List<String> description) {
        this.displayMaterial = displayMaterial;
        this.durability = (short) durability;
        this.name = name;
        this.perkRarity = rarity;
        this.soulWell = soulWellPerk;
        this.description = description;
    }

    public AbstractPerk(Material displayMaterial, int durability, String name, PerkRarity rarity, boolean soulWellPerk, String description) {
        this(displayMaterial, durability, name, rarity, soulWellPerk, new ArrayList<>(Collections.singletonList(description)));
    }

    public AbstractPerk(Material displayMaterial, int durability, String name, PerkRarity rarity, List<String> description) {
        this(displayMaterial, durability, name, rarity, true, description);
    }

    public AbstractPerk(Material displayMaterial, int durability, String name, PerkRarity rarity, String description) {
        this(displayMaterial, durability, name, rarity, true, description);
    }

    public AbstractPerk(Material displayMaterial, String name, PerkRarity rarity, boolean soulWellPerk, String description) {
        this(displayMaterial, 0, name, rarity, soulWellPerk, description);
    }


    public AbstractPerk(Material material, String name, PerkRarity rarity, List<String> description) {
        this(material, 0, name, rarity, true, description);
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

    @Override
    public boolean isSoulWellPerk() {
        return perkRarity == null || perkRarity == PerkRarity.SOUL_WELL;
    }
}
