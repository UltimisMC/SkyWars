package com.ultimismc.skywars.core.user.asset;

import com.ultimismc.skywars.core.game.features.Purchasable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
@Getter
public class UserAsset {

    private final Purchasable purchasable;
    @Setter private boolean activated;
    private long acquiredAt;

    public UserAsset(Purchasable purchasable, long acquiredAt, boolean activated) {
        this(purchasable);
        this.acquiredAt = acquiredAt;
        this.activated = activated;
    }

    public String getName() {
        return purchasable.getName();
    }

    public String getNameWithCategory() {
        return purchasable.getName() + " " + purchasable.getCategory();
    }

    public int getPrice() {
        return purchasable.getPrice();
    }

    public String getDisplayPrice() {
        return purchasable.getDisplayPrice();
    }

    public void toggleAsset() {
        this.activated = !activated;
    }
}
