package com.ultimismc.skywars.lobby.shop.level;

import com.ultimismc.skywars.core.game.features.level.Level;
import com.ultimismc.skywars.core.game.features.level.LevelReward;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserStatistics;
import com.ultimismc.skywars.lobby.LobbyManager;
import com.ultimismc.skywars.lobby.shop.UserProduct;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import xyz.directplan.directlib.shop.ProductItemDesign;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DirectPlan
 */
public class NextLevelDisplayProduct extends UserProduct {

    private final LobbyManager lobbyManager;
    public NextLevelDisplayProduct(LobbyManager lobbyManager, int itemSlot) {
        super("Next Level Display Product", itemSlot);
        this.lobbyManager = lobbyManager;
    }

    @Override
    public ProductItemDesign designProduct(User user) {

        Level level = user.getLevel();
        Level nextLevel = level.getNext();
        if(nextLevel == null) return null;

        UserStatistics userStatistics = user.getStatistics();
        int currentProgress = userStatistics.getNextLevelProgress();
        int nextRequiredProgress = user.getNextRequiredExp();

        String currentProgressStr = String.valueOf(currentProgress);
        String maxProgress = String.valueOf(nextRequiredProgress);

        Material material = nextLevel.getPrestigeMaterial();
        String displayName = nextLevel.getPrestigeColor() + "Level " + nextLevel.getOrder();
        List<String> lore = new ArrayList<>();

        lore.add(" ");
        lore.add("&7Progress: &b" + currentProgressStr + "&7/&a" + maxProgress);
        String progressBar = lobbyManager.getProgressBar(currentProgress, nextRequiredProgress);
        lore.add("&8[" + progressBar + "&8]");
        lore.add(" ");
        lore.add("&aRewards:");
        for(LevelReward levelReward : nextLevel.getRewards()) {
            lore.add(levelReward.getDisplayName());
        }

        ProductItemDesign productItemDesign = new ProductItemDesign(material, ChatColor.WHITE, lore);
        String prestigeTexture = nextLevel.getPrestigeTexture();
        if(prestigeTexture != null) {
            productItemDesign.setSkullTexture(prestigeTexture);
        }
        productItemDesign.setDisplayName(displayName);
        return productItemDesign;
    }

    @Override
    public void executeAction(User user) {}
}
