package com.ultimismc.skywars.core.game.features.kits;

import com.ultimismc.skywars.core.game.features.Purchasable;

import java.util.List;

/**
 * @author DirectPlan
 */
public interface Kit extends Purchasable {

    KitRarity getKitRarity();

    List<KitItem> getItems();

    List<String> getDisplayItems();
}
