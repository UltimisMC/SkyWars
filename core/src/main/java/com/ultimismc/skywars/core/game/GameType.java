package com.ultimismc.skywars.core.game;

import lombok.Getter;
import org.bukkit.ChatColor;

/**
 * @author DirectPlan
 */
@Getter
public enum GameType {

    NORMAL(ChatColor.GREEN, "Normal"),
    INSANE(ChatColor.RED, "Insane"),
    ;

    private final ChatColor color;
    private final String name;

    GameType(ChatColor color, String name) {
        this.name = name;
        this.color = color;
    }

    public String getDisplayName() {
        return this.color + this.name;
    }
}
