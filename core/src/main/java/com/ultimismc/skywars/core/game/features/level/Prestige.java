package com.ultimismc.skywars.core.game.features.level;

import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import xyz.directplan.directlib.StringUtil;

/**
 * @author DirectPlan
 */
@Getter
public class Prestige {

    private String prestigeTexture;
    private final Material prestigeMaterial;
    private short durability;
    private final String name;
    private final ChatColor color;
    private final String icon;
    private final int requiredLevel;
    private final boolean rainbow;

    public Prestige(Material prestigeMaterial, String name, ChatColor color, String icon, int requiredLevel, boolean rainbow) {
        this.prestigeMaterial = prestigeMaterial;
        this.name = name;
        this.color = color;
        this.icon = icon;
        this.requiredLevel = requiredLevel;
        this.rainbow = rainbow;
    }

    public Prestige(Material prestigeMaterial, short durability, String name, ChatColor color, String icon, int requiredLevel, boolean rainbow) {
        this(prestigeMaterial, name, color, icon, requiredLevel, rainbow);
        this.durability = durability;
    }

    public Prestige(String prestigeTexture, String name, ChatColor color, String icon, int requiredLevel, boolean rainbow) {
        this(Material.SKULL_ITEM, name, color, icon, requiredLevel, rainbow);
        this.prestigeTexture = prestigeTexture;
    }

    public Prestige(Material prestigeMaterial, String name, ChatColor color, String icon, int requiredLevel) {
        this(prestigeMaterial, name, color, icon, requiredLevel, false);
    }

    public Prestige(String prestigeTexture, String name, ChatColor color, String icon, int requiredLevel) {
        this(prestigeTexture, name, color, icon, requiredLevel, false);
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

    public String getUncoloredDisplayName(String extra) {
        return name + " Prestige" + (extra != null ? " " + extra : "");
    }

    public String getUncoloredDisplayName() {
        return getUncoloredDisplayName(null);
    }

    public String getDisplayName(String extra) {
        String displayName = getUncoloredDisplayName(extra);
        if(rainbow) {
            return StringUtil.toRainbow(displayName);
        }
        return color + displayName;
    }
}
