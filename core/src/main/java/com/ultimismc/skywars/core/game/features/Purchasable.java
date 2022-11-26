package com.ultimismc.skywars.core.game.features;

import com.ultimismc.skywars.core.game.currency.Currency;

/**
 * @author DirectPlan
 */
public interface Purchasable {

    String getName();

    String getCategory();

    int getPrice();

    PurchasableDesign getDesign();

    boolean isSoulWell();

    Currency getCurrency();

    PurchasableRarity getRarity();

    default boolean isDefault() {
        return false;
    }

    default String getDisplayPrice() {
        return getCurrency().getDisplayAmount(getPrice());
    }

    default String getNameWithCategory() {
        return getName() + " " + getCategory();
    }
}
