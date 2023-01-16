package com.ultimismc.skywars.core.game.currency;

import com.ultimismc.skywars.core.game.features.Purchasable;
import com.ultimismc.skywars.core.user.User;
import org.bukkit.ChatColor;
import xyz.directplan.directlib.StringUtil;

/**
 * @author DirectPlan
 */
public interface Currency {

    Currency COIN_CURRENCY = new CoinCurrency();
    Currency SOUL_CURRENCY = new SoulCurrency();
    Currency EXP_CURRENCY = new ExpCurrency();

    String getName();

    ChatColor getCurrencyColor();

    void increaseCurrency(User user, int amount);

    default void increaseCurrencyWithMessage(User user, int amount) {
        increaseCurrency(user, amount);
        sendMessage(user, amount, null);
    }

    default void increaseCurrencyReason(User user, int amount, String reason) {
        increaseCurrency(user, amount);
        sendMessage(user, amount, reason);
    }

    void decreaseCurrency(User user, int amount);

    default void decreaseCurrency(User user, Purchasable purchasable) {
        decreaseCurrency(user, purchasable.getPrice());
    }

    default void sendMessage(User user, int amount, String reason) {
        String displayAmount = getDisplayAmount(amount);
        ChatColor color = getCurrencyColor();
        user.sendMessage(color + "+ " + displayAmount + (reason != null ? " (" + reason + color + ")" : ""));
    }

    default String getDisplayAmount(int amount) {
        return getCurrencyColor() + StringUtil.getReadableNumber(amount);
    }

    boolean canAfford(User user, int amount);

    default boolean canAfford(User user, Purchasable purchasable) {
        return canAfford(user, purchasable.getPrice());
    }
}
