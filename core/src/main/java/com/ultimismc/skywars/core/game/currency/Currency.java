package com.ultimismc.skywars.core.game.currency;

import com.ultimismc.skywars.core.game.features.Purchasable;
import com.ultimismc.skywars.core.user.User;
import org.bukkit.ChatColor;

/**
 * @author DirectPlan
 */
public interface Currency {

    Currency COIN_CURRENCY = new CoinCurrency();
    Currency SOUL_CURRENCY = new SoulCurrency();

    ChatColor getCurrencyColor();

    void increaseCurrency(User user, int amount);

    default void increaseCurrency(User user, Purchasable purchasable) {
        increaseCurrency(user, purchasable.getPrice());
    }

    void decreaseCurrency(User user, int amount);

    default void decreaseCurrency(User user, Purchasable purchasable) {
        decreaseCurrency(user, purchasable.getPrice());
    }

    boolean canAfford(User user, int amount);

    default boolean canAfford(User user, Purchasable purchasable) {
        return canAfford(user, purchasable.getPrice());
    }
}
