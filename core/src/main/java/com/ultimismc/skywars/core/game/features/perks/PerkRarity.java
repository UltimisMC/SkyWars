package com.ultimismc.skywars.core.game.features.perks;

import com.ultimismc.skywars.core.game.features.PurchasableRarity;
import lombok.Getter;
import org.bukkit.ChatColor;

/**
 * @author DirectPlan
 */
@Getter
public enum PerkRarity {

    LEGENDARY(PurchasableRarity.LEGENDARY, 150000), // 150000
    RARE(PurchasableRarity.RARE, 50000), // 50000
    COMMON(PurchasableRarity.COMMON, 5000), // 5000
    SOUL_WELL(PurchasableRarity.COMMON, -1),
    ;

    private final PurchasableRarity purchasableRarity;
    private final int price;

    PerkRarity(PurchasableRarity purchasableRarity, int price) {
        this.purchasableRarity = purchasableRarity;
        this.price = price;
    }

    public int getPriority() {
        return purchasableRarity.getPriority();
    }

    public String getDisplayName() {
        return purchasableRarity.getDisplayName();
    }
}
