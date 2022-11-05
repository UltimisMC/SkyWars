package com.ultimismc.skywars.core.game.features.level;

import lombok.Getter;
import org.bukkit.ChatColor;
import xyz.directplan.directlib.StringUtil;

/**
 * @author DirectPlan
 */
@Getter
public class Prestige {

    private final String name;
    private final ChatColor color;
    private final String icon;
    private final int requiredLevel;
    private final boolean rainbow;

    public Prestige(String name, ChatColor color, String icon, int requiredLevel, boolean rainbow) {
        this.name = name;
        this.color = color;
        this.icon = icon;
        this.requiredLevel = requiredLevel;
        this.rainbow = rainbow;
    }

    public Prestige(String name, ChatColor color, String icon, int requiredLevel) {
        this(name, color, icon, requiredLevel, false);
    }

    public String getIconLevelDisplay(int level, Prestige prestigeIcon, boolean brackets) {
        String iconDisplay = level + icon;
        if(prestigeIcon != null) {
            iconDisplay = level + prestigeIcon.getIcon();
        }
        if(brackets) {
            iconDisplay = "[" + iconDisplay + "]";
        }
        if(rainbow) {
            return StringUtil.toRainbow(iconDisplay);
        }
        return color + iconDisplay;
    }

    public String getIconDisplayName() {
        return getDisplayName("Icon");
    }

    public String getDisplayName() {
        return getDisplayName(null);
    }

    public String getDisplayName(String extra) {
        String displayName = name + " Prestige" + (extra != null ? " " + extra : "");
        if(rainbow) {
            return StringUtil.toRainbow(displayName);
        }
        return color + displayName;
    }
}
