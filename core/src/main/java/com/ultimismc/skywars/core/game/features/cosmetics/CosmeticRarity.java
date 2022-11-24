package com.ultimismc.skywars.core.game.features.cosmetics;

import com.ultimismc.skywars.core.game.features.PurchasableRarity;
import lombok.Getter;

/**
 * @author DirectPlan
 */
@Getter
public enum CosmeticRarity {

    LEGENDARY(PurchasableRarity.LEGENDARY, 100000), // 30000
    EPIC(PurchasableRarity.EPIC, 25000), // 20000
    RARE(PurchasableRarity.RARE, 10000), // 20000
    COMMON(PurchasableRarity.COMMON, 5000); // 15000

    private final PurchasableRarity purchasableRarity;
    private final int price;

    CosmeticRarity(PurchasableRarity purchasableRarity, int price) {
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
