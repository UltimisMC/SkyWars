package com.ultimismc.skywars.core.game.features.level;

import com.ultimismc.skywars.core.user.User;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class PrestigeIconLevelReward implements LevelReward {

    private final Prestige prestige;

    @Override
    public String getDisplayName() {
        return prestige.getIconDisplayName() + " " + ChatColor.WHITE + prestige.getIcon();
    }

    @Override
    public void rewardPlayer(User user) {

    }
}
