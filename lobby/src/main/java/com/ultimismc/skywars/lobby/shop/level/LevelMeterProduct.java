package com.ultimismc.skywars.lobby.shop.level;

import com.ultimismc.skywars.core.game.features.level.Level;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserStatistics;
import com.ultimismc.skywars.lobby.LobbyManager;
import com.ultimismc.skywars.lobby.shop.UserProduct;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.ClickType;
import xyz.directplan.directlib.shop.ProductItemDesign;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DirectPlan
 */
public class LevelMeterProduct extends UserProduct {

    private final LobbyManager lobbyManager;

    private final int levelPercentage;

    public LevelMeterProduct(LobbyManager lobbyManager, int meterNum, int itemSlot) {
        super("Level Meter", itemSlot);

        this.lobbyManager = lobbyManager;
        levelPercentage = (meterNum * 20);
    }

    @Override
    public ProductItemDesign designProduct(User user) {
        Level level = user.getLevel();
        UserStatistics userStatistics = user.getStatistics();
        int currentProgress = userStatistics.getNextLevelProgress();
        int nextRequiredProgress = user.getNextRequiredExp();

        String currentProgressStr = "MAXED";
        String maxProgress = "MAXED";
        boolean hasNextLevel = nextRequiredProgress != -1;
        if(hasNextLevel) {
            currentProgressStr = String.valueOf(currentProgress);
            maxProgress = String.valueOf(nextRequiredProgress);
        }

        Material material = Material.STAINED_GLASS_PANE;
        String displayName = level.getPrestigeColor() + "Level " + level.getOrder();
        List<String> lore = new ArrayList<>();

        lore.add(" ");
        lore.add("&7Progress: &b" + currentProgressStr + "&7/&a" + maxProgress);
        String progressBar = lobbyManager.getProgressBar(currentProgress, nextRequiredProgress);
        lore.add("&8[" + progressBar + "&8]");

        short durability = 4;
        float levelPercentage = (float) ((currentProgress * 100) / nextRequiredProgress);

        if(levelPercentage >= this.levelPercentage) {
            durability = 5;
        }
        ProductItemDesign productItemDesign = new ProductItemDesign(material, durability, ChatColor.WHITE, lore);
        productItemDesign.setDisplayName(displayName);
        return productItemDesign;
    }

    @Override
    public void executeAction(User user, ClickType clickType) {}
}
