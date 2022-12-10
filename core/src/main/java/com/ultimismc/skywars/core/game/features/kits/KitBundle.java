package com.ultimismc.skywars.core.game.features.kits;

import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

/**
 * @author DirectPlan
 */
public abstract class KitBundle {

    @Getter private final List<KitItem> items = new LinkedList<>();

    public abstract void buildGameItems();

    public abstract PurchasableDesign getDesign();

    public void addItem(KitItem kitItem) {
        items.add(kitItem);
    }
}
