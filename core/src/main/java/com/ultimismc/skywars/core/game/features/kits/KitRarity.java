package com.ultimismc.skywars.core.game.features.kits;

import lombok.Getter;
import org.bukkit.ChatColor;

/**
 * @author DirectPlan
 */
@Getter
public enum KitRarity {

    LEGENDARY(1, ChatColor.GOLD, 30000), // 30000
    RARE(2, ChatColor.BLUE, 20000), // 20000
    COMMON(3, ChatColor.GREEN, 0); // 15000

    private final int priority;
    private final ChatColor color;
    private final int price;

    KitRarity(int priority, ChatColor color, int price) {
        this.priority = priority;
        this.color = color;
        this.price = price;
    }

    public String getDisplayName() {
        return (color + name());
    }
}
