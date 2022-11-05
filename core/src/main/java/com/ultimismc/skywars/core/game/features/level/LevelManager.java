package com.ultimismc.skywars.core.game.features.level;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.features.FeatureInitializer;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserStatistics;
import lombok.Getter;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author DirectPlan
 */
@Getter
public class LevelManager implements FeatureInitializer {

    private final String name = "SkyWars Level Progression";

    private final SkyWarsPlugin plugin;
    private final LinkedList<Level> levelList = new LinkedList<>();
    private final List<Prestige> prestiges = new ArrayList<>();

    public LevelManager(SkyWarsPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void initializeFeature(SkyWarsPlugin plugin) {

        prestiges.add(new Prestige("Default", ChatColor.GRAY, "★", 1));
        prestiges.add(new Prestige("Iron", ChatColor.WHITE, "✙", 5));
        prestiges.add(new Prestige("Gold", ChatColor.GOLD, "❤", 10));
        prestiges.add(new Prestige("Diamond", ChatColor.AQUA, "☠", 15));
        prestiges.add(new Prestige("Emerald", ChatColor.DARK_GREEN, "✦", 20));
        prestiges.add(new Prestige("Sapphire", ChatColor.DARK_AQUA, "✌", 25));
        prestiges.add(new Prestige("Ruby", ChatColor.DARK_RED, "❦", 30));
        prestiges.add(new Prestige("Crystal", ChatColor.LIGHT_PURPLE, "✵", 35));
        prestiges.add(new Prestige("Opal", ChatColor.BLUE, "❣", 40));
        prestiges.add(new Prestige("Amethyst", ChatColor.DARK_PURPLE, "☯", 45));
        prestiges.add(new Prestige("Rainbow", ChatColor.WHITE, "✺", 50, true));
        prestiges.add(new Prestige("Mythic", ChatColor.WHITE, "ಠ_ಠ", 60, true));

        int constantExpRequirement = 10000;
        int[] initiateExpRequirements = {0, 20, 50, 80, 100, 250, 500, 1000, 1500, 2500, 4000, 5000};
        int maximumLevel = 60;
        for(int levelOrder = 1; levelOrder <= maximumLevel; levelOrder++) {
            int expRequirement = constantExpRequirement;
            if(levelOrder <= initiateExpRequirements.length) {
                expRequirement = initiateExpRequirements[(levelOrder - 1)];
            }
            List<LevelReward> levelRewards = new ArrayList<>();
            Prestige levelPrestige = getPrestigeAtLevel(levelOrder);
            if(levelPrestige != null && levelPrestige.getRequiredLevel() == levelOrder) {
                levelRewards.add(new PrestigeLevelReward(levelPrestige));
                levelRewards.add(new PrestigeIconLevelReward(levelPrestige));
            }
            Level level = new Level(levelOrder, expRequirement, levelPrestige);

            level.addAllRewards(levelRewards);
            levelList.addLast(level);
        }

        plugin.log("Loaded " + levelList.size() + " levels, and " + prestiges.size() + " prestiges!");
    }

    public Prestige getPrestigeAtLevel(int level) {
        Prestige found = null;
        for(Prestige prestige : prestiges) {
            int requiredLevel = prestige.getRequiredLevel();
            if(level >= requiredLevel) {
                found = prestige;
            }
        }
        return found;
    }

    public void calculateUserLevel(User user) {
        UserStatistics userStatistics = user.getStatistics();
        int totalExp = userStatistics.getTotalExp();
        Level foundLevel = null;
        Prestige prestigeIcon = null;
        for(Level level : levelList) {
            int requiredExp = level.getRequiredExp();
            totalExp -= requiredExp;

            if(totalExp >= 0) {
                Prestige levelPrestige = level.getPrestige();
                if(levelPrestige != null) {
                    prestigeIcon = levelPrestige;
                }
                foundLevel = level;
                continue;
            }
            break;
        }
        user.setLevel(foundLevel);
        if(user.getSelectedPrestigeIcon() == null) {
            user.setSelectedPrestigeIcon(prestigeIcon);
        }
    }
}
