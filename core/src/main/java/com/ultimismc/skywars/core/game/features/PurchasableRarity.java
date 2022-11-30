package com.ultimismc.skywars.core.game.features;

import lombok.Getter;
import org.bukkit.ChatColor;

/**
 * @author DirectPlan
 */
@Getter
public enum PurchasableRarity {

    COMMON(4, 100, ChatColor.GREEN),
    RARE(3, 15, ChatColor.BLUE),
    EPIC(2, 10, ChatColor.DARK_PURPLE),
    LEGENDARY(1, 6, ChatColor.GOLD);

    private final int priority, occurrenceChance;
    private final ChatColor color;

    PurchasableRarity(int priority, int occurrenceChance, ChatColor color) {
        this.priority = priority;
        this.occurrenceChance = occurrenceChance;
        this.color = color;
    }

    public String getDisplayName() {
        return (color + name());
    }

    public boolean isRare() {
        return priority <= 3;
    }
}
