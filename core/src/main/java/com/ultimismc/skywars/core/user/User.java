package com.ultimismc.skywars.core.user;

import com.ultimismc.skywars.core.game.GameType;
import com.ultimismc.skywars.core.game.currency.Currency;
import com.ultimismc.skywars.core.game.features.Purchasable;
import com.ultimismc.skywars.core.game.features.level.Level;
import com.ultimismc.skywars.core.game.features.level.Prestige;
import com.ultimismc.skywars.core.rank.Rank;
import com.ultimismc.skywars.core.user.asset.UserAsset;
import com.ultimismc.skywars.core.user.asset.UserAssetsHandler;
import com.ultimismc.skywars.core.user.setting.UserSetting;
import com.ultimismc.skywars.core.user.setting.UserSettingHandler;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.PluginUtility;
import xyz.directplan.directlib.inventory.PlayerInventoryLayout;
import xyz.directplan.directlib.inventory.UserInventory;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author DirectPlan
 */
@Data
@Getter
@Setter
public class User implements UserInventory {

    private final UUID uuid;

    private Player player;
    private String name;
    private boolean online;
    private Rank rank;

    private boolean scramble;
    private Prestige selectedPrestigeIcon;

    private UserStatistics statistics = new UserStatistics();
    private UserAssetsHandler userAssetsHandler = new UserAssetsHandler();
    private UserSettingHandler userSettingHandler = new UserSettingHandler();

    private PlayerInventoryLayout inventoryLayout;


    public String getName() {
        if(player != null) return player.getName();
        return name;
    }

    public String getDisplayName() {
        String name = getName();
        if(rank == null) return name;
        return rank.getColor() + name;
    }

    public void teleport(Location location) {
        if(location == null) return;
        if(player != null) player.teleport(location);
    }

    public void sendMessage(String message) {
        if(player != null) player.sendMessage(PluginUtility.translateMessage(message));
    }

    public Collection<UserAsset> getAssets() {
        return userAssetsHandler.getAssets();
    }

    public boolean hasAsset(Purchasable purchasable, GameType gameType) {
        return getAsset(purchasable, gameType) != null;
    }

    public UserAsset getAsset(Purchasable purchasable, GameType gameType) {
        return userAssetsHandler.getAsset(purchasable, gameType);
    }

    public <T extends Purchasable> List<UserAsset> getAssets(Class<T> clazz) {
        return userAssetsHandler.getAssets(clazz);
    }

    public <T extends Purchasable> List<T> getAssetPurchasables(Class<T> clazz) {
        return userAssetsHandler.getAssetPurchasables(clazz);
    }

    public void addAssetAllModes(UserAsset asset) {
        for(GameType gameType : GameType.values()) {
            addAsset(asset, gameType);
        }
    }
    public void addAsset(UserAsset asset, GameType gameType) {
        userAssetsHandler.addAsset(asset, gameType);
    }

    public void purchaseAsset(Purchasable purchasable, GameType gameType) {
        userAssetsHandler.purchaseAsset(purchasable, gameType);
    }

    public boolean hasPurchased(Purchasable purchasable, GameType gameType) {
        return userAssetsHandler.hasPurchased(purchasable, gameType);
    }

    public boolean canAfford(Purchasable purchasable) {
        if(purchasable == null) return false;
        Currency currency = purchasable.getCurrency();
        return currency.canAfford(this, purchasable);
    }

    public <T extends Purchasable> List<UserAsset> getActivatedAssets(Class<T> clazz, GameType gameType) {
        Stream<UserAsset> stream = getAssets(clazz).stream().filter(UserAsset::isActivated);
        if(gameType != null) stream = stream.filter(asset -> asset.isPurchasedFor(gameType));
        return stream.collect(Collectors.toList());
    }

    public <T extends Purchasable> List<UserAsset> getActivatedAssets(Class<T> clazz) {
        return getActivatedAssets(clazz, null);
    }


    public <T extends Purchasable> int getActivatedAssetsSize(Class<T> clazz) {
        return getActivatedAssets(clazz).size();
    }

    public <T> void addSetting(String key, T value) {
        userSettingHandler.addSetting(key, value);
    }

    public <T> void setSetting(String key, T value) {
        userSettingHandler.setSetting(key, value);
    }

    public <T> T getSetting(Class<T> castClass, String key) {
        return userSettingHandler.getSetting(castClass, key);
    }

    public Collection<UserSetting> getSettings() {
        return userSettingHandler.getSettings();
    }

    public boolean hasEmptySettings() {
        return userSettingHandler.isEmpty();
    }

    public String getLevelDisplayName(Level level, boolean brackets) {
        return level.getDisplayName(selectedPrestigeIcon, brackets);
    }

    public String getLevelDisplayName(Level level) {
        return level.getDisplayName(selectedPrestigeIcon, false);
    }

    public String getLevelDisplayName(boolean brackets) {
        Level level = statistics.getLevel();
        return getLevelDisplayName(level, brackets);
    }

    public String getLevelDisplayName() {
        return getLevelDisplayName(false);
    }

    public Level getLevel() {
        return statistics.getLevel();
    }

    public int getNextRequiredExp() {
        Level nextLevel = getLevel().next;
        if(nextLevel != null) {
            return nextLevel.getRequiredExp();
        }
        return -1;
    }

    public void setLevel(Level level) {
        statistics.setLevel(level);
    }

    public boolean hasPrestigeAccess(Prestige prestige) {
        int requiredLevel = prestige.getRequiredLevel();

        Level level = statistics.getLevel();
        return level.isExceedingThan(requiredLevel);
    }

    public Collection<String> getFavouriteMaps() {
        return userSettingHandler.getListSetting(String.class, "favouritemaps");
    }

    public boolean hasFavouriteMap(String map) {
        return userSettingHandler.contains("favouritemaps", map);
    }

    public void addFavouriteMap(String map) {
        userSettingHandler.addSetting("favouritemaps", map);
    }

    public void removeFavouriteMap(String map) {
        userSettingHandler.removeSetting("favouritemaps", map);
    }
}
