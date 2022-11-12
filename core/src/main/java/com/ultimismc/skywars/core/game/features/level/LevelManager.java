package com.ultimismc.skywars.core.game.features.level;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.currency.Currency;
import com.ultimismc.skywars.core.game.features.FeatureInitializer;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserStatistics;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

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

        String sapphireTexture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZThjNTExYjk2MWIyZGNhMDEyZjNhNWY1YjQ2NjA3OGEwZWMyMzgwYTc2ZDUxOWVhMzY0YTdkNmRjMjhlMWJiIn19fQ==";
        String rubyTexture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjMxNzUyODZjZDNiYTFhM2E5YzkwODI5NzdkMDlkZDM3YjE3N2FiZjM3YTQ2NjU4MGMyN2QxZGVlNzJiM2MxOCJ9fX0=";
        String opalTexture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjI1NWE3ODUxYjQ3ODYxOTJhNDFmODhkYzJkMjhkNGU5Yjc3MjNiMDZlNWNlMmY2YTQxOGJkZTYxMzI2YmMxMiJ9fX0=";
        String amethystTexture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTc5ZjhjOTI3NzZkNjQyZDExOWY5ZTkyMzYwYjFlNWI5NzFlNjZlNjE0MjhhM2UxYjMxMWQ4YjYxODVlNiJ9fX0=";
        String rainbowTexture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjdiNzQ3YjM3OGE0MWEwYTZlZGM4NmMwMDBmMDQwYzY5OTRhODMzMjUxMTk2YzlkNTJjMmEyMzBmOTUxNjBjYyJ9fX0=";
        prestiges.add(new Prestige(Material.COAL, "Default", ChatColor.GRAY, "★", 1));
        prestiges.add(new Prestige(Material.IRON_INGOT, "Iron", ChatColor.WHITE, "✙", 5));
        prestiges.add(new Prestige(Material.GOLD_INGOT, "Gold", ChatColor.GOLD, "❤", 10));
        prestiges.add(new Prestige(Material.DIAMOND, "Diamond", ChatColor.AQUA, "☠", 15));
        prestiges.add(new Prestige(Material.EMERALD, "Emerald", ChatColor.DARK_GREEN, "✦", 20));
        prestiges.add(new Prestige(sapphireTexture, "Sapphire", ChatColor.DARK_AQUA, "✌", 25));
        prestiges.add(new Prestige(rubyTexture, "Ruby", ChatColor.DARK_RED, "❦", 30));
        prestiges.add(new Prestige(Material.QUARTZ, "Crystal", ChatColor.LIGHT_PURPLE, "✵", 35));
        prestiges.add(new Prestige(opalTexture, "Opal", ChatColor.BLUE, "❣", 40));
        prestiges.add(new Prestige(amethystTexture, "Amethyst", ChatColor.DARK_PURPLE, "☯", 45));
        prestiges.add(new Prestige(rainbowTexture, "Rainbow", ChatColor.WHITE, "✺", 50, true));
        prestiges.add(new Prestige(Material.SKULL, (short)1, "Mythic", ChatColor.WHITE, "ಠ_ಠ", 60, true));

        int constantExpRequirement = 10000;
        int constantCoinsReward = 15000;
        int[] initiateExpRequirements = {0, 20, 50, 80, 100, 250, 500, 1000, 1500, 2500, 4000, 5000};
        int[] initiateCoinsRewards = {250, 500, 750, 1000, 1500, 2000, 2500, 3500, 4500, 7500, 90000, 10000};
        int maximumLevel = 60;

        Level lastLevel = null;
        int totalExpRequired = 0;
        for(int levelOrder = 1; levelOrder <= maximumLevel; levelOrder++) {
            int expRequirement = constantExpRequirement;
            int levelOrderIndex = (levelOrder - 1);
            if(levelOrder <= initiateExpRequirements.length) {
                expRequirement = initiateExpRequirements[levelOrderIndex];
            }

            int coinsReward = constantCoinsReward;
            if(levelOrder <= initiateCoinsRewards.length) {
                coinsReward = initiateCoinsRewards[levelOrderIndex];
            }

            List<LevelReward> levelRewards = new ArrayList<>();
            levelRewards.add(new CoinLevelReward(coinsReward));
            Prestige levelPrestige = getPrestigeAtLevel(levelOrder);
            if(levelPrestige != null && levelPrestige.getRequiredLevel() == levelOrder) {
                levelRewards.add(new PrestigeLevelReward(levelPrestige));
                levelRewards.add(new PrestigeIconLevelReward(levelPrestige));
            }
            totalExpRequired += expRequirement;
            Level level = new Level(levelOrder, expRequirement, totalExpRequired, levelPrestige);
            if(lastLevel != null) {
                lastLevel.next = level;
            }
            level.addAllRewards(levelRewards);
            levelList.addLast(level);
            lastLevel = level;
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

    public int getExpProgressOf(User user, Level level) {
        UserStatistics userStatistics = user.getStatistics();
        int totalExpRequired = level.getTotalExpRequired();
        int requiredExp = level.getRequiredExp();

        int totalExp = userStatistics.getTotalExp();
        if(totalExp <= 0) {
            return 0;
        }
        int bulkProgressLeft = (totalExpRequired - totalExp);

        int progress = (requiredExp - bulkProgressLeft);
        if(progress > requiredExp) {
            progress = requiredExp;
        }
        if(progress < 0) progress = 0;
        return progress;
    }

    public void giveExp(User user, int experience) {
        Currency.EXP_CURRENCY.increaseCurrencyWithMessage(user, experience);

        Level level = user.getLevel();
        calculateUserLevel(user);
        if(user.getLevel() == level) { // If it's the same, then we don't celebrate
            return;
        }
        // CELEBRATE!!
        Player player = user.getPlayer();
        player.playSound(player.getLocation(), Sound.LEVEL_UP, 1f, 1f);
        user.sendMessage("&aYou are now SkyWars level " + user.getLevelDisplayName() + "&a!");
    }

    public void calculateUserLevel(User user) {
        UserStatistics userStatistics = user.getStatistics();
        int totalExp = userStatistics.getTotalExp();
        Level foundLevel = null;
        for(Level level : levelList) {
            int totalExpRequired = level.getTotalExpRequired();
            if(totalExp >= totalExpRequired) {
                foundLevel = level;
                continue;
            }
            break;
        }
        if(foundLevel == null) {
            throw new RuntimeException("An estimated level was not found for " + user.getName() + ".");
        }
        user.setLevel(foundLevel);
        int nextLevelProgress = -1;
        Level nextLevel = foundLevel.next;
        if(nextLevel != null) {
            nextLevelProgress = getExpProgressOf(user, nextLevel);
        }
        userStatistics.setNextLevelProgress(nextLevelProgress);
    }
}
