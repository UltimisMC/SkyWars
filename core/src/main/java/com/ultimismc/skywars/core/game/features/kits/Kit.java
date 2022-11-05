package com.ultimismc.skywars.core.game.features.kits;

import com.ultimismc.skywars.core.game.features.Purchasable;

import java.util.List;

/**
 * @author DirectPlan
 */
public interface Kit extends Purchasable {

    KitRarity getRarity();

    List<KitItem> getItems();
}
