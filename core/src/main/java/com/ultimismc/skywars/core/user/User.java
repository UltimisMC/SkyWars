package com.ultimismc.skywars.core.user;

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
import xyz.directplan.directlib.inventory.InventoryUser;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * @author DirectPlan
 */
@Data
@Getter
@Setter
public class User implements InventoryUser<UserPlayerInventoryUi> {

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

    private UserPlayerInventoryUi currentInventoryUi;


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

    public UserAsset getAsset(Purchasable purchasable) {
        return userAssetsHandler.getAsset(purchasable);
    }

    public <T extends Purchasable> List<T> getAssets(Class<T> clazz) {
        return userAssetsHandler.getAssets(clazz);
    }

    public void addAsset(UserAsset asset) {
        userAssetsHandler.addAsset(asset);
    }

    public void purchaseAsset(Purchasable purchasable) {
        userAssetsHandler.purchaseAsset(purchasable);
    }

    public boolean hasPurchased(Purchasable purchasable) {
        return userAssetsHandler.hasPurchased(purchasable);
    }

    public boolean canAfford(Purchasable purchasable) {
        if(purchasable == null) return false;
        Currency currency = purchasable.getCurrency();
        return currency.canAfford(this, purchasable);
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
    
    @Override
    public UserPlayerInventoryUi getInventoryUi() {
        return currentInventoryUi;
    }
}
