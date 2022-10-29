package com.ultimismc.skywars.core.user;

import com.ultimismc.skywars.core.game.currency.Currency;
import com.ultimismc.skywars.core.game.features.Purchasable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.PluginUtility;
import xyz.directplan.directlib.inventory.InventoryUser;

import javax.print.attribute.standard.PrinterURI;
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

    private UserStatistics statistics = new UserStatistics();
    private UserAssetsHandler userAssetsHandler = new UserAssetsHandler();

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

    @Override
    public UserPlayerInventoryUi getInventoryUi() {
        return currentInventoryUi;
    }
}
