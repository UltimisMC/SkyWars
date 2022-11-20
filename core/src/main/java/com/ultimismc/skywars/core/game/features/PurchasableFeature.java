package com.ultimismc.skywars.core.game.features;

/**
 * @author DirectPlan
 */
public interface PurchasableFeature<T extends Purchasable> extends FeatureInitializer, PurchasableRepository<T> {}
