package com.ultimismc.skywars.core.game.features.level;

import com.ultimismc.skywars.core.game.currency.Currency;
import com.ultimismc.skywars.core.user.User;
import lombok.RequiredArgsConstructor;
import xyz.directplan.directlib.StringUtil;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class CoinLevelReward implements LevelReward {

    private final Currency currency = Currency.COIN_CURRENCY;
    private final int coins;

    @Override
    public String getDisplayName() {
        return "&8+" + currency.getCurrencyColor() + StringUtil.getReadableNumber(coins) + " &7SkyWars Coins";
    }

    @Override
    public void rewardPlayer(User user) {
        Currency.COIN_CURRENCY.increaseCurrency(user, coins);
    }
}
