package com.ultimismc.skywars.lobby.shop.soulwell.rewards;

import com.ultimismc.skywars.core.game.currency.Currency;
import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.PurchasableRarity;
import com.ultimismc.skywars.core.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import xyz.directplan.directlib.PluginUtility;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class BagOfCoinsReward implements SoulWellReward {

    private final BagType bagType;

    @Override
    public void giveReward(User user) {
        String displayName = getDisplayName();
        Currency.COIN_CURRENCY.increaseCurrencyReason(user, bagType.getCoins(), displayName);
    }

    @Override
    public String getDisplayName() {
        return getRarity().getColor() + bagType.getName() + " bag of coins";
    }

    @Override
    public PurchasableRarity getRarity() {
        return bagType.getRarity();
    }

    @Override
    public PurchasableDesign getDesign() {
        return new PurchasableDesign(Material.DOUBLE_PLANT);
    }

    @Override
    public int getRarityCoins() {
        return bagType.getCoins();
    }

    public static BagOfCoinsReward getRandomBagOfCoinsReward() {
        BagType randomBagType = PluginUtility.getRandomElement(BagType.values());
        return new BagOfCoinsReward(randomBagType);
    }

    @Getter
    enum BagType {

        SMALL(PurchasableRarity.COMMON, "Small", 50),
        MEDIUM(PurchasableRarity.RARE, "Medium", 500),
        LARGE(PurchasableRarity.LEGENDARY, "Large", 2500);

        private final PurchasableRarity rarity;
        private final String name;
        private final int coins;

        BagType(PurchasableRarity rarity, String name, int coins) {
            this.rarity = rarity;
            this.name = name;
            this.coins = coins;
        }

    }
}
