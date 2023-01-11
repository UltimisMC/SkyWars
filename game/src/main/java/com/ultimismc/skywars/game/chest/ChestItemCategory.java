package com.ultimismc.skywars.game.chest;

import lombok.Getter;

/**
 * @author DirectPlan
 */
public enum ChestItemCategory {

    WEAPON(100, 1),
    ARMOR(90, 2),
    BLOCKS(100, 2),
    MISCELLANEOUS(100, 4),
    FOOD(90, 1),
    POTIONS(90, 2),
    TOOLS(90, 1),
    ;

    @Getter private final int percentage, maximumItems;

    ChestItemCategory(int percentage, int maximumItems) {
        this.percentage = percentage;
        this.maximumItems = maximumItems;
    }
}
