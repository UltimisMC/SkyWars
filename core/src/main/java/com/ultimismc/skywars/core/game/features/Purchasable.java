package com.ultimismc.skywars.core.game.features;

import com.ultimismc.skywars.core.game.currency.Currency;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
public interface Purchasable {

    String getName();

    String getCategory();

    int getPrice();

    Material getDisplayMaterial();

    short getDisplayDurability();

    Currency getCurrency();

    default String getDisplayPrice() {
        return String.valueOf(getCurrency().getCurrencyColor()) + getPrice();
    }
}
