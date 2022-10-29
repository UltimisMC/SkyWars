package com.ultimismc.skywars.core.game.features.perks;

import lombok.Getter;
import org.bukkit.ChatColor;

/**
 * @author DirectPlan
 */
@Getter
public enum PerkRarity {

    LEGENDARY(1, ChatColor.GOLD, 150000), // 150000
    RARE(2, ChatColor.BLUE, 50000), // 50000
    COMMON(3, ChatColor.GREEN, 0); // 5000

    private final int priority;
    private final ChatColor color;
    private final int price;

    PerkRarity(int priority, ChatColor color, int price) {
        this.priority = priority;
        this.color = color;
        this.price = price;
    }

    public String getDisplayName() {
        return (color + name());
    }
}
