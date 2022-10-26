package com.ultimismc.skywars.core.game.features.kits;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DirectPlan
 */
@Getter
public abstract class AbstractKit implements Kit {

    private final KitRarity rarity;
    private final String name;
    private final int price;

    private final List<KitItem> items = new ArrayList<>();


    public AbstractKit(KitRarity rarity, String name, int price) {
        this.rarity = rarity;
        this.name = name;
        this.price = price;
    }

    public void addKitItem(KitItem item) {
        items.add(item);
    }
}
