package com.ultimismc.skywars.core.game.features;

import java.util.Map;

/**
 * @author DirectPlan
 */
public interface PurchasableRepository<T extends Purchasable> {

    Map<String, T> getPurchasables();
}
