package com.ultimismc.skywars.core.user;

import com.ultimismc.skywars.core.game.currency.Currency;
import com.ultimismc.skywars.core.game.features.Purchasable;
import com.ultimismc.skywars.core.game.features.level.Level;
import com.ultimismc.skywars.core.game.features.level.Prestige;
import com.ultimismc.skywars.core.rank.Rank;
import com.ultimismc.skywars.core.user.asset.UserAsset;
import com.ultimismc.skywars.core.user.asset.UserAssetsHandler;
import com.ultimismc.skywars.core.user.setting.UserSettingHandler;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.PluginUtility;
import xyz.directplan.directlib.inventory.InventoryUser;

import java.util.Collection;
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

    public void addAsset(UserAsset asset) {
        userAssetsHandler.addAsset(asset);
    }

    public void purchaseAsset(Purchasable purchasable) {
        userAssetsHandler.purchaseAsset(this, purchasable);
    }

    public boolean canAfford(Purchasable purchasable) {
        if(purchasable == null) return false;
        Currency currency = purchasable.getCurrency();
        return currency.canAfford(this, purchasable);
    }

    public String getLevelDisplayName(boolean brackets) {
        Level level = statistics.getLevel();
        return level.getDisplayName(selectedPrestigeIcon, brackets);
    }

    public String getLevelDisplayName() {
        return getLevelDisplayName(false);
    }

    public Level getLevel() {
        return statistics.getLevel();
    }

    public void setLevel(Level level) {
        statistics.setLevel(level);
    }

    @Override
    public UserPlayerInventoryUi getInventoryUi() {
        return currentInventoryUi;
    }
}
