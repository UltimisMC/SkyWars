package com.ultimismc.skywars.lobby.shop.level;

import com.ultimismc.skywars.core.game.features.level.Level;
import com.ultimismc.skywars.core.game.features.level.Prestige;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserStatistics;
import com.ultimismc.skywars.lobby.LobbyManager;
import com.ultimismc.skywars.lobby.config.ShopMessageKeys;
import com.ultimismc.skywars.lobby.shop.UserProductCategory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import xyz.directplan.directlib.StringUtil;
import xyz.directplan.directlib.config.replacement.Replacement;
import xyz.directplan.directlib.shop.ProductItemDesign;

import java.util.List;

/**
 * @author DirectPlan
 */
public class LevelProductCategory extends UserProductCategory {

    private final LobbyManager lobbyManager;
    public LevelProductCategory(LobbyManager lobbyManager, int itemSlot) {
        super("SkyWars Level Progression", itemSlot);
        this.lobbyManager = lobbyManager;
    }

    @Override
    public ProductItemDesign designCategory(User user) {


        UserStatistics userStatistics = user.getStatistics();
        Level level = userStatistics.getLevel();

        int currentProgress = userStatistics.getNextLevelProgress();
        int nextRequiredProgress = user.getNextRequiredExp();

        String currentPrestigeStr = user.getLevelDisplayName();
        String nextPrestigeStr = "";

        String currentProgressStr = "MAXED";
        String maxProgress = "MAXED";
        if(nextRequiredProgress != -1) {
            currentProgressStr = String.valueOf(currentProgress);
            maxProgress = String.valueOf(nextRequiredProgress);

            Level nextLevel = level.getNext();
            nextPrestigeStr = user.getLevelDisplayName(nextLevel);
        }

        String progressBar = lobbyManager.getProgressBar(currentProgress, nextRequiredProgress);

        List<String> lore = ShopMessageKeys.LEVEL_PROGRESSION_CATEGORY_LORE
                .getStringList(new Replacement("current-progress", currentProgressStr),
                        new Replacement("maximum-progress", maxProgress),
                        new Replacement("current-prestige", currentPrestigeStr),
                        new Replacement("next-prestige", nextPrestigeStr),
                        new Replacement("progress-bar", "&8["+progressBar+"&8]"));
        return new ProductItemDesign(Material.NETHER_STAR, ChatColor.LIGHT_PURPLE, lore);
    }

}
