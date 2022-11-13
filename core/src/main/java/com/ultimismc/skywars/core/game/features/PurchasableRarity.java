package com.ultimismc.skywars.core.game.features;

import lombok.Getter;
import org.bukkit.ChatColor;

/**
 * @author DirectPlan
 */
@Getter
public enum PurchasableRarity {

    COMMON(3, 100, ChatColor.GREEN),
    RARE(2, 12, ChatColor.BLUE),
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
}
