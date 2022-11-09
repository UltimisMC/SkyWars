package com.ultimismc.skywars.core.game.features.level;

import lombok.Data;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author DirectPlan
 */
@Data
public class Level {

    public Level next;

    private final int order, requiredExp, totalExpRequired;
    private final Prestige prestige;

    private final List<LevelReward> rewards = new ArrayList<>();

    public void addReward(LevelReward levelReward) {
        rewards.add(levelReward);
    }

    public void addAllRewards(List<LevelReward> levelRewards) {
        rewards.addAll(levelRewards);
    }
    public void addAllRewards(LevelReward... levelRewards) {
        addAllRewards(Arrays.asList(levelRewards));
    }

    public String getDisplayName(Prestige icon, boolean brackets) {
        return prestige.getIconLevelDisplay(order, icon, brackets);
    }

    public String getDisplayName(Prestige prestigeIcon) {
        return getDisplayName(prestigeIcon, false);
    }

    public String getDisplayName() {
        return getDisplayName(null);
    }

    public ChatColor getPrestigeColor() {
        return prestige.getColor();
    }

    public Material getPrestigeMaterial() {
        return prestige.getPrestigeMaterial();
    }

    public String getPrestigeTexture() {
        return prestige.getPrestigeTexture();
    }
    
    public boolean isExceedingThan(int level) {
        return order >= level;
    }
    
    public boolean hasRewards() {
        return !rewards.isEmpty();
    }
}
