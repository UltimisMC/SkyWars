package com.ultimismc.skywars.game.chest;

import lombok.Getter;

/**
 * @author DirectPlan
 */
@Getter
public enum RefillPhase {

    FIRST(3, 6, false, false),
    SECOND(6, 9, false, true),
    THIRD(6, 9, true, true)
    ,;

    private final int minimumChestItems,  maximumChestItems;
    private final boolean enderpearl, compass;

    RefillPhase(int minimumChestItems, int maximumChestItems, boolean enderpearl, boolean compass) {
        this.minimumChestItems = minimumChestItems;
        this.maximumChestItems = maximumChestItems;
        this.enderpearl = enderpearl;
        this.compass = compass;
    }

    public boolean hasEnderpearl() {
        return enderpearl;
    }

    public boolean hasCompass() {
        return compass;
    }
}
