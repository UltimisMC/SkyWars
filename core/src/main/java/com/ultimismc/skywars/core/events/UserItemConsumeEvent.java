package com.ultimismc.skywars.core.events;

import com.ultimismc.skywars.core.game.GameConfig;
import com.ultimismc.skywars.core.user.User;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.event.Cancellable;
import org.bukkit.inventory.ItemStack;

/**
 * @author DirectPlan
 */
@Getter
public class UserItemConsumeEvent extends UserSkyWarsEvent implements Cancellable {

    @Setter private boolean cancelled;
    private final ItemStack item;


    public UserItemConsumeEvent(GameConfig gameConfig, User user, ItemStack item) {
        super(gameConfig, user);

        this.item = item;
    }

    public boolean isType(Material material) {
        return item.getType() == material;
    }

    public boolean isBlockItem() {
        Material material = item.getType();
        return material.isBlock();
    }
}
