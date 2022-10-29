package com.ultimismc.skywars.core.user;

import com.ultimismc.skywars.core.game.currency.Currency;
import com.ultimismc.skywars.core.game.features.Purchasable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
@Getter
public class UserAsset {

    private final Purchasable purchasable;
    private boolean activated;
    private long acquiredAt;

    public UserAsset(Purchasable purchasable, long acquiredAt, boolean activated) {
        this(purchasable);
        this.acquiredAt = acquiredAt;
        this.activated = activated;
    }

    public String getName() {
        return purchasable.getName() + " " + purchasable.getCategory();
    }

    public int getPrice() {
        return purchasable.getPrice();
    }

    public String getDisplayPrice() {
        return purchasable.getDisplayPrice();
    }
}
