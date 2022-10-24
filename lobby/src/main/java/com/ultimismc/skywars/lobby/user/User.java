package com.ultimismc.skywars.lobby.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.PluginUtility;
import xyz.directplan.directlib.inventory.InventoryUser;

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

    @Override
    public UserPlayerInventoryUi getInventoryUi() {
        return currentInventoryUi;
    }
}
